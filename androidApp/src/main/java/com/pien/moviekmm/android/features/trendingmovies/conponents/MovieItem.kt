package com.pien.moviekmm.android.features.trendingmovies.conponents

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pien.moviekmm.BuildConfig
import com.pien.moviekmm.android.core.components.RatingBar
import com.pien.moviekmm.android.core.components.ImagePosterView
import com.pien.moviekmm.core.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(movie: Movie,
    modifier: Modifier = Modifier,
    onClickMovie: (Int, String) -> Unit) {
    Card(shape = RoundedCornerShape(8),
        modifier = modifier
            .width(300.dp)
            .padding(8.dp),
        onClick = { onClickMovie(movie.id, movie.posterPath.toString()) },
        colors = CardDefaults.cardColors(containerColor = Color(0x55FFFFFF))) {
        ImagePosterView(
            urlPath = BuildConfig.IMAGE_URL + movie.posterPath,
            modifier = modifier
                .height(400.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = movie.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = modifier
                .padding(top = 8.dp)
                .padding(horizontal = 2.dp),
            maxLines = 2
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "Release in " + movie.releaseDate,
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

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        movie = Movie(id = 0,
            title = "abc",
            posterPath = "",
            releaseDate = "20-06-2025",
            voteAverage = 1.2,
            voteCount = 1000),
        onClickMovie = { _,_ -> }
    )
}