package com.pien.moviekmm.android.features.moviedetails.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pien.moviekmm.android.features.moviedetails.presentation.AndroidMovieDetailViewModel
import com.pien.moviekmm.android.features.moviedetails.presentation.MovieDetailScreen
import com.pien.moviekmm.features.moviedetails.MovieDetailEvent

const val MOVIE_DETAIL_ROUTE = "movie_detail/{movie_id}/{poster_path}"
fun NavGraphBuilder.movieDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable(
        route = MOVIE_DETAIL_ROUTE
    ) { navBackStackEntry ->
        val movieId = navBackStackEntry.arguments?.getString("movie_id")
        val posterPath = navBackStackEntry.arguments?.getString("poster_path")
        movieId?.let {
            val viewModel = hiltViewModel<AndroidMovieDetailViewModel>()
            LaunchedEffect(movieId) {
                viewModel.onEvent(MovieDetailEvent.GetMovieDetail(it.toInt()))
            }
            val state by viewModel.state.collectAsState()

            MovieDetailScreen(
                uiState = state,
                posterPath = "/$posterPath",
                modifier = Modifier,
                onBackPressed = onNavigateUp
            )
        }
    }
}