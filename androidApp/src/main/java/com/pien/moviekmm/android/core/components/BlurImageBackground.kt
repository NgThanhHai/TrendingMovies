package com.pien.moviekmm.android.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.skydoves.cloudy.cloudy

@Composable
fun BlurImageBackground(modifier: Modifier, radius: Int, url: String) {
    Box(modifier = Modifier.cloudy(radius = radius)) {
        if (url.isNotEmpty()) {
            ImagePosterView(modifier = modifier, urlPath = url)
        }
    }
}