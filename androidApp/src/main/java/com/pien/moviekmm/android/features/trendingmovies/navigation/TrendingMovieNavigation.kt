package com.pien.moviekmm.android.features.trendingmovies.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pien.moviekmm.android.features.trendingmovies.presentation.AndroidTrendingMovieViewModel
import com.pien.moviekmm.android.features.trendingmovies.presentation.TrendingMoviesScreen
import com.pien.moviekmm.features.trendingmovies.TrendingMovieEvent

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.trendingMovieScreen(
    transitionScope: SharedTransitionScope,
    onOpenMovieDetail: (Int, String, String) -> Unit) {
    composable<TrendingMovieRoute> {
        val viewModel = hiltViewModel<AndroidTrendingMovieViewModel>()
        val state by viewModel.state.collectAsState()
        TrendingMoviesScreen(
            uiState = state,
            modifier = Modifier,
            animatedContentScope = this,
            sharedTransitionScope = transitionScope,
            onEvent = {
                when (it) {
                    is TrendingMovieEvent.OpenMovieDetail -> {
                        onOpenMovieDetail(it.movieId, it.title, it.poster)
                    }
                    else -> viewModel.onEvent(it)
                }
            })
    }
}