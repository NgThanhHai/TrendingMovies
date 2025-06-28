package com.pien.moviekmm.android.features.trendingmovies.conponents

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pien.moviekmm.core.domain.model.Movie

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TrendingMoviesCarousel(modifier: Modifier = Modifier,
    listState: LazyListState,
    flingBehavior: FlingBehavior,
    listMovies: List<Movie>,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClickMovie: (Int, String, String) -> Unit) {
    LazyRow(
        state = listState,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        flingBehavior = flingBehavior
    ) {
        items(listMovies, key = { it.posterPath.toString() }) { movie ->
            MovieItem(movie = movie,
                modifier = modifier,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                onClickMovie = { id, title, posterPath ->
                    onClickMovie(id, title, posterPath)
                })
        }
    }
}