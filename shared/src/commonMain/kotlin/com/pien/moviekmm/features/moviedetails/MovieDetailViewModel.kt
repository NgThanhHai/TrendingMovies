package com.pien.moviekmm.features.moviedetails

import com.pien.moviekmm.core.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.pien.moviekmm.core.data.response.Result

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    coroutineScope: CoroutineScope?) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(MovieDetailUIState())
    val state: StateFlow<MovieDetailUIState> = _state

    fun onEvent(event: MovieDetailEvent, isNetworkAvailable: Boolean) {
        when(event) {
            is MovieDetailEvent.GetMovieDetail -> {
                getMovieDetail(event.id, isNetworkAvailable)
            }
        }
    }

    private fun getMovieDetail(id: Int, isNetworkAvailable: Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(showLoading = true, error = "")
            }
            when(val result = getMovieDetailUseCase.execute(id, isNetworkAvailable)) {
                is Result.Failure -> {
                  _state.update {
                      it.copy(showLoading = false, error = result.error.toString())
                  }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(showLoading = false, movieDetail = result.data)
                    }
                }
            }
        }
    }
}