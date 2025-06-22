package com.pien.moviekmm.android.app.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pien.moviekmm.android.features.moviedetails.navigation.MOVIE_DETAIL_ROUTE
import com.pien.moviekmm.android.features.moviedetails.navigation.movieDetailScreen
import com.pien.moviekmm.android.features.trendingmovies.navigation.TRENDING_MOVIE_ROUTE
import com.pien.moviekmm.android.features.trendingmovies.navigation.trendingMovieScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = TRENDING_MOVIE_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        trendingMovieScreen(
            onOpenMovieDetail = { id, posterPath ->
                navController.navigate(MOVIE_DETAIL_ROUTE.replace("{movie_id}", id.toString()).replace("{poster_path}", posterPath.removePrefix("/")))
            }
        )
        movieDetailScreen(
            onNavigateUp = {
                navController.navigateUp()
            })
    }
}