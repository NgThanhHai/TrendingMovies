package com.pien.moviekmm.features.trendingmovies

import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.domain.model.Movie
import com.pien.moviekmm.core.domain.usecase.GetTrendingMoviesUseCase
import com.pien.moviekmm.core.domain.usecase.SearchTrendingMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TrendingMovieViewModel(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val searchTrendingMoviesUseCase: SearchTrendingMoviesUseCase,
    coroutineScope: CoroutineScope?) {
    
    // use coroutineScope for android, otherwise use Default Main Dispatchers (iOS)
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private var cacheListMovies: List<Movie> = listOf()

    private val _state = MutableStateFlow(TrendingMovieUIState())
    val state: StateFlow<TrendingMovieUIState> = _state

    fun onEvent(event: TrendingMovieEvent) {
        when(event) {
            is TrendingMovieEvent.SearchMovies -> {
                queryMovies(event.query)
            }
            is TrendingMovieEvent.UpdateTrendingMovies -> {
                getTrendingMovies()
            }
            else -> {}
        }
    }

    private var queryMoviesJob: Job? = null
    private fun queryMovies(query: String) {
        if (query.isEmpty() && cacheListMovies.isNotEmpty()) {
            _state.update {
                it.copy(listMovies = cacheListMovies.toList(), searchText = query, errorToast = "", showReload = false)
            }
            return
        }
        queryMoviesJob?.cancel()
        queryMoviesJob = viewModelScope.launch {
            _state.update {
                it.copy(showLoading = true, searchText = query, errorToast = "", showReload = false)
            }
            when(val result = searchTrendingMoviesUseCase.execute(query)) {
                is Result.Success -> {
                    _state.update {
                        it.copy(showLoading = false,
                            listMovies = result.data.results)
                    }
                }
                is Result.Failure -> {
                    if(result.error == DataError.Network.NO_INTERNET) {
                        _state.update {
                            it.copy(showLoading = false,
                                errorToast = DataError.Network.NO_INTERNET.toString(),
                                showReload = true
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(showLoading = false,
                                errorToast = result.error.toString()
                            )
                        }
                    }
                }
            }
        }
    }

    private var getTrendingMoviesJob: Job? = null
    private fun getTrendingMovies() {
        getTrendingMoviesJob = viewModelScope.launch {
            _state.update {
                it.copy(showLoading = true)
            }
            when(val result = getTrendingMoviesUseCase.execute()) {
                is Result.Success -> {
                    cacheListMovies = result.data.results
                    val backgroundUrl = cacheListMovies.maxByOrNull { it.voteAverage ?: 0.0 }?.posterPath ?: ""
                    _state.update {
                        it.copy(showLoading = false,
                            listMovies = result.data.results,
                            backgroundUrl = backgroundUrl)
                    }
                }
                is Result.Failure -> {
                    _state.update {
                        it.copy(showLoading = false,
                            errorToast = result.error.toString()
                        )
                    }
                }
            }
        }
    }
}