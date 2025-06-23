package com.pien.moviekmm.android.features.moviedetails.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pien.moviekmm.android.features.moviedetails.presentation.AndroidMovieDetailViewModel
import com.pien.moviekmm.android.features.moviedetails.presentation.MovieDetailScreen
import com.pien.moviekmm.features.moviedetails.MovieDetailEvent

fun NavGraphBuilder.movieDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable<MovieDetailRoute> { navBackStackEntry ->
        val movieDetail: MovieDetailRoute = navBackStackEntry.toRoute()
        val viewModel = hiltViewModel<AndroidMovieDetailViewModel>()
        LaunchedEffect(movieDetail.movieId) {
            viewModel.onEvent(MovieDetailEvent.GetMovieDetail(movieDetail.movieId))
        }
        val state by viewModel.state.collectAsState()

        MovieDetailScreen(
            uiState = state,
            posterPath = "/${movieDetail.posterUrl}",
            modifier = Modifier,
            onBackPressed = onNavigateUp
        )
    }
}