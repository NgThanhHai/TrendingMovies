package com.pien.moviekmm.android.app.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pien.moviekmm.android.features.moviedetails.navigation.MovieDetailRoute
import com.pien.moviekmm.android.features.moviedetails.navigation.movieDetailScreen
import com.pien.moviekmm.android.features.trendingmovies.navigation.TrendingMovieRoute
import com.pien.moviekmm.android.features.trendingmovies.navigation.trendingMovieScreen

@SuppressLint("UnusedSharedTransitionModifierParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = TrendingMovieRoute,
            popEnterTransition = { EnterTransition.None },
            popExitTransition = {
                fadeOut(animationSpec = tween(200))
            },
            modifier = modifier
        ) {
            trendingMovieScreen(
                transitionScope = this@SharedTransitionLayout,
                onOpenMovieDetail = { id, title, posterPath ->
                    navController.navigate(MovieDetailRoute(movieId = id, movieTitle = title, posterUrl = posterPath))
                }
            )
            movieDetailScreen(
                transitionScope = this@SharedTransitionLayout,
                onNavigateUp = {
                    navController.navigateUp()
                })
        }
    }

}