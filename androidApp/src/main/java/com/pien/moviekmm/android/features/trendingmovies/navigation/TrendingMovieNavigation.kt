package com.pien.moviekmm.android.features.trendingmovies.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pien.moviekmm.android.features.trendingmovies.presentation.AndroidTrendingMovieViewModel
import com.pien.moviekmm.android.features.trendingmovies.presentation.TrendingMoviesScreen
import com.pien.moviekmm.features.trendingmovies.TrendingMovieEvent

const val TRENDING_MOVIE_ROUTE = "trending_movie"
fun NavGraphBuilder.trendingMovieScreen(onOpenMovieDetail: (Int, String) -> Unit) {
    composable(route = TRENDING_MOVIE_ROUTE) {
        val viewModel = hiltViewModel<AndroidTrendingMovieViewModel>()
        val state by viewModel.state.collectAsState()
        val gridState = rememberLazyListState()
        val snappingLayout = remember(gridState) { SnapLayoutInfoProvider(gridState) }
        val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
        TrendingMoviesScreen(
            uiState = state,
            modifier = Modifier,
            state = gridState,
            flingBehavior = flingBehavior,
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