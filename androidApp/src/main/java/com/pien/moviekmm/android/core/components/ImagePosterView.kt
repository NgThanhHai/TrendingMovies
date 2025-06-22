package com.pien.moviekmm.android.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.pien.moviekmm.BuildConfig

@Composable
fun ImagePosterView(modifier: Modifier = Modifier, urlPath: String){
    AsyncImage(
        model = "${BuildConfig.IMAGE_URL}$urlPath",
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}