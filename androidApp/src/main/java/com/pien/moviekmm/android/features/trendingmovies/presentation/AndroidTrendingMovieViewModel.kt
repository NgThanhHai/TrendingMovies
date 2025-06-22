package com.pien.moviekmm.android.features.trendingmovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pien.moviekmm.core.domain.usecase.GetTrendingMoviesUseCase
import com.pien.moviekmm.core.domain.usecase.SearchTrendingMoviesUseCase
import com.pien.moviekmm.features.trendingmovies.TrendingMovieEvent
import com.pien.moviekmm.features.trendingmovies.TrendingMovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTrendingMovieViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val searchTrendingMoviesUseCase: SearchTrendingMoviesUseCase
): ViewModel() {

    private val viewModel by lazy {
        TrendingMovieViewModel(
            getTrendingMoviesUseCase = getTrendingMoviesUseCase,
            searchTrendingMoviesUseCase = searchTrendingMoviesUseCase,
            coroutineScope = viewModelScope)
    }

    init {
        viewModel.onEvent(TrendingMovieEvent.UpdateTrendingMovies)
    }

    val state = viewModel.state

    fun onEvent(event: TrendingMovieEvent) {
        viewModel.onEvent(event)
    }
}