package com.pien.moviekmm.android.features.trendingmovies.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pien.moviekmm.android.features.trendingmovies.presentation.AndroidTrendingMovieViewModel
import com.pien.moviekmm.android.features.trendingmovies.presentation.TrendingMoviesScreen
import com.pien.moviekmm.features.trendingmovies.TrendingMovieEvent

fun NavGraphBuilder.trendingMovieScreen(onOpenMovieDetail: (Int, String) -> Unit) {
    composable<TrendingMovieRoute> {
        val viewModel = hiltViewModel<AndroidTrendingMovieViewModel>()
        val state by viewModel.state.collectAsState()
        TrendingMoviesScreen(
            uiState = state,
            modifier = Modifier,
            onEvent = {
                when (it) {
                    is TrendingMovieEvent.OpenMovieDetail -> {
                        onOpenMovieDetail(it.movieId, it.poster)
                    }
                    else -> viewModel.onEvent(it)
                }
            })
    }
}