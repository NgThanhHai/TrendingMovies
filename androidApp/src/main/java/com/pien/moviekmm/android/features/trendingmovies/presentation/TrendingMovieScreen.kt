package com.pien.moviekmm.android.features.trendingmovies.presentation

import NoConnectionScreen
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pien.moviekmm.android.core.components.BlurImageBackground
import com.pien.moviekmm.android.core.components.LoadingScreen
import com.pien.moviekmm.android.core.components.MovieSearchAppBar
import com.pien.moviekmm.android.features.trendingmovies.conponents.TrendingMoviesCarousel
import com.pien.moviekmm.features.trendingmovies.TrendingMovieEvent
import com.pien.moviekmm.features.trendingmovies.TrendingMovieUIState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TrendingMoviesScreen(
    modifier: Modifier = Modifier,
    uiState: TrendingMovieUIState,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onEvent: (TrendingMovieEvent) -> Unit
) {
    val listState = rememberLazyListState()
    val snappingLayout = remember(listState) { SnapLayoutInfoProvider(listState) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
    BlurImageBackground(modifier.fillMaxSize(), radius = 15, url =  uiState.backgroundUrl)
    Column(modifier = modifier) {
        MovieSearchAppBar(modifier = modifier,
            searchText = uiState.searchText, onQueryChanged = { query ->
            onEvent(TrendingMovieEvent.SearchMovies(query))
        })
        if(uiState.showLoading) {
            LoadingScreen(modifier = modifier.height(48.dp).width(48.dp).padding(8.dp))
        } else if(uiState.errorToast.isNotEmpty()) {
            NoConnectionScreen(modifier = modifier.padding(30.dp), uiState.errorToast)
        } else {
            TrendingMoviesCarousel(
                modifier = modifier,
                listState = listState,
                flingBehavior = flingBehavior,
                animatedContentScope = animatedContentScope,
                sharedTransitionScope = sharedTransitionScope,
                listMovies = uiState.listMovies,
            ) { id, title, posterPath ->
                onEvent(TrendingMovieEvent.OpenMovieDetail(id, title, posterPath))
            }
        }
    }
}


@SuppressLint("UnusedSharedTransitionModifierParameter", "UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun TrendingMoviesScreenPreview() {
    SharedTransitionLayout {
        AnimatedContent(targetState = "") { _ ->
            TrendingMoviesScreen(
                modifier = Modifier,
                uiState = TrendingMovieUIState(),
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedContentScope = this,
                onEvent = {}
            )
        }
    }
}