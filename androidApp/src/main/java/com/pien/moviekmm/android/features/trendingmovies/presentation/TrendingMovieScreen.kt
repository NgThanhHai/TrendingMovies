package com.pien.moviekmm.android.features.trendingmovies.presentation

import NoConnectionScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pien.moviekmm.android.core.components.ImagePosterView
import com.pien.moviekmm.android.core.components.IndeterminateCircularIndicator
import com.pien.moviekmm.android.features.trendingmovies.conponents.MovieItem
import com.pien.moviekmm.android.features.trendingmovies.conponents.MovieSearchBar
import com.pien.moviekmm.features.trendingmovies.TrendingMovieEvent
import com.pien.moviekmm.features.trendingmovies.TrendingMovieUIState
import com.skydoves.cloudy.cloudy

@Composable
fun TrendingMoviesScreen(
    modifier: Modifier = Modifier,
    uiState: TrendingMovieUIState,
    state: LazyListState,
    flingBehavior: FlingBehavior,
    onEvent: (TrendingMovieEvent) -> Unit
) {
    Box(modifier = modifier) {
        if (uiState.backgroundUrl.isNotEmpty()) {
            ImagePosterView(modifier = modifier.fillMaxSize().cloudy(50),
                urlPath = uiState.backgroundUrl)
        }
    }
    Column(modifier = modifier) {
        Text(if(uiState.searchText.isNotEmpty()) "Search Results" else "Trending Movies",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            modifier = modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(50.dp))
        MovieSearchBar(modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .height(60.dp),
            searchText = uiState.searchText,
            showReload = uiState.showReload,
            onQueryChange = { onEvent(TrendingMovieEvent.SearchMovies(it)) },
            onClearText = {onEvent(TrendingMovieEvent.SearchMovies(""))})

        Spacer(modifier = modifier.height(16.dp))
        if(uiState.showLoading) {
            Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
                IndeterminateCircularIndicator(
                    modifier
                        .height(48.dp)
                        .width(48.dp)
                        .padding(8.dp))
            }
        } else if(uiState.errorToast.isNotEmpty()) {
            NoConnectionScreen(modifier = modifier.padding(30.dp).fillMaxSize(), uiState.errorToast)
        } else {
            LazyRow(
                state = state,
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                flingBehavior = flingBehavior
            ) {
                items(uiState.listMovies) { movie ->
                    MovieItem(movie = movie,
                        modifier = modifier,
                        onClickMovie = { id, posterPath ->
                            onEvent(TrendingMovieEvent.OpenMovieDetail(id, posterPath))
                        })
                }
            }
        }
    }
}


@Preview
@Composable
fun TrendingMoviesScreenPreview() {
    val gridState = rememberLazyListState()
    val snappingLayout = remember(gridState) { SnapLayoutInfoProvider(gridState) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
    TrendingMoviesScreen(
        modifier = Modifier,
        uiState = TrendingMovieUIState(),
        state = gridState,
        flingBehavior = flingBehavior,
        onEvent = {}
    )
}