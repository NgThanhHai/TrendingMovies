package com.pien.moviekmm.android.features.trendingmovies.conponents

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pien.moviekmm.android.R
import com.pien.moviekmm.android.core.components.RatingBar
import com.pien.moviekmm.android.core.components.ImagePosterView
import com.pien.moviekmm.core.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MovieItem(movie: Movie,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClickMovie: (Int, String, String) -> Unit) {
    Card(shape = RoundedCornerShape(8),
        modifier = modifier
            .width(300.dp)
            .padding(8.dp),
        onClick = { onClickMovie(movie.id, movie.title, movie.posterPath.toString()) },
        colors = CardDefaults.cardColors(containerColor = Color(0x55FFFFFF))) {
        with(sharedTransitionScope) {
            ImagePosterView(
                urlPath = movie.posterPath.toString(),
                modifier = modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = movie.posterPath.toString()),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = { _, _ -> tween(durationMillis = 500) })
            )
            Text(
                text = movie.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 2.dp)
                    .sharedElement(sharedTransitionScope.rememberSharedContentState(key = movie.title),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 500)
                        }
                    ),
                maxLines = 2
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(R.string.str_movie_description_release_date) + movie.releaseDate,
            fontSize = 16.sp,
            color = Color.White,
            modifier = modifier
                .padding(vertical = 8.dp)
                .padding(horizontal = 4.dp)
        )
        RatingBar((movie.voteAverage ?: 0).toFloat()/2, modifier
            .padding(vertical = 8.dp)
            .padding(horizontal = 4.dp)
            .height(20.dp))
    }
    Spacer(modifier = modifier.height(16.dp))
}

@SuppressLint("UnusedSharedTransitionModifierParameter", "UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun MovieItemPreview() {
    SharedTransitionScope {
        AnimatedContent(targetState = "") { _ ->
            MovieItem(
                movie = Movie(id = 0,
                    title = stringResource(R.string.str_preview_movie_title),
                    posterPath = "",
                    releaseDate = stringResource(R.string.str_preview_date_20_06_2025),
                    voteAverage = 1.2,
                    voteCount = 1000),
                sharedTransitionScope = this@SharedTransitionScope,
                animatedContentScope = this,
                onClickMovie = { _,_,_ -> }
            )
        }
    }
}