package com.pien.moviekmm.android.features.moviedetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pien.moviekmm.core.domain.usecase.GetMovieDetailUseCase
import com.pien.moviekmm.features.moviedetails.MovieDetailEvent
import com.pien.moviekmm.features.moviedetails.MovieDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase): ViewModel() {

        private val viewModel by lazy {
            MovieDetailViewModel(
                getMovieDetailUseCase = getMovieDetailUseCase,
                coroutineScope = viewModelScope)
        }

    val state = viewModel.state

    fun onEvent(event: MovieDetailEvent) {
        viewModel.onEvent(event)
    }
}