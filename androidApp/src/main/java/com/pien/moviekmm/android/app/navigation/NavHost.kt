package com.pien.moviekmm.android.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pien.moviekmm.android.features.moviedetails.navigation.MovieDetailRoute
import com.pien.moviekmm.android.features.moviedetails.navigation.movieDetailScreen
import com.pien.moviekmm.android.features.trendingmovies.navigation.TrendingMovieRoute
import com.pien.moviekmm.android.features.trendingmovies.navigation.trendingMovieScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TrendingMovieRoute,
        modifier = modifier
    ) {
        trendingMovieScreen(
            onOpenMovieDetail = { id, posterPath ->
                navController.navigate(MovieDetailRoute(movieId = id, posterUrl = posterPath))
            }
        )
        movieDetailScreen(
            onNavigateUp = {
                navController.navigateUp()
            })
    }
}