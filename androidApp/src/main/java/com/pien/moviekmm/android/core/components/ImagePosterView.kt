package com.pien.moviekmm.android.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pien.moviekmm.SharedBuildConfig

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImagePosterView(modifier: Modifier = Modifier, urlPath: String){
    GlideImage(
        model = "${SharedBuildConfig.IMAGE_URL}$urlPath",
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}