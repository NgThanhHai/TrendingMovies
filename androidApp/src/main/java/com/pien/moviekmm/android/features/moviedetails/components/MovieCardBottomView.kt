package com.pien.moviekmm.android.features.moviedetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pien.moviekmm.android.core.components.RatingBar
import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.domain.model.MovieDetail.Companion.getListGenresContent

@Composable
fun MovieCardBottomView(modifier: Modifier = Modifier, movie: MovieDetail) {
    Column(modifier = modifier.background(Color(0x55000000)),
        verticalArrangement = Arrangement.Bottom) {
        DescriptionText(modifier = modifier.padding(top = 8.dp),
            title = "Release Date: ",
            description = "${movie.releaseDate}")
        if (!movie.genres.isNullOrEmpty()) {
            val textToShow = movie.genres?.getListGenresContent() ?: ""
            DescriptionText(modifier = modifier.padding(top = 8.dp),
                title = "Genres: ",
                description = textToShow)
        }
        movie.voteAverage?.let {
            Row(modifier = modifier.padding(top = 8.dp)) {
                Text(
                    text = "Rating: ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = modifier
                )
                RatingBar(it.toFloat()/2, modifier.height(18.dp))
                DescriptionText(modifier = modifier,
                    title = "by ",
                    description = "${movie.voteCount} viewers")
            }
        }

        DescriptionText(modifier = modifier.padding(top = 8.dp),
            title = "Time: ",
            description = "${movie.runtime} minutes")

        DescriptionText(modifier = modifier.padding(top = 8.dp),
            title = "Content: ",
            description = if (movie.adult == true) {
                "Adults only"
            } else {
                "For all ages"
            })

        DescriptionText(modifier = modifier.padding(top = 8.dp),
            title = "Overview: ",
            description = movie.overview.toString())


        movie.homepage?.let { homepageUrl ->
            val showText = "For more information: $homepageUrl"
            HyperlinkText(modifier = modifier.padding(top = 8.dp),
                fullText = showText,
                linkText = listOf(homepageUrl),
                hyperlinks = listOf(homepageUrl),
                fontSize = 16.sp,
                linkTextColor = Color.Blue,
                titleColor = Color.White)
        }
        Spacer(modifier.height(20.dp))
    }
}