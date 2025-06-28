package com.pien.moviekmm.android.features.moviedetails.presentation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pien.moviekmm.android.R
import com.pien.moviekmm.android.core.components.BlurImageBackground
import com.pien.moviekmm.android.core.components.LoadingScreen
import com.pien.moviekmm.android.features.moviedetails.components.MovieCardBottomView
import com.pien.moviekmm.features.moviedetails.MovieDetailUIState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MovieDetailScreen(
    uiState: MovieDetailUIState,
    title: String,
    posterPath: String,
    modifier: Modifier,
    transitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBackPressed: () -> Unit
) {
    val movie = uiState.movieDetail
    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.error) {
        if (uiState.error.isNotEmpty()) {
            Toast.makeText(context, uiState.error, Toast.LENGTH_LONG).show()
        }
    }
    BackHandler {
        onBackPressed()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0x55FFFFFF),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    with(transitionScope) {
                        Text(text = title,
                            modifier = Modifier.sharedElement(transitionScope.rememberSharedContentState(key = title),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 500)
                                }
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.str_back_button_content),
                            modifier = Modifier.clickable {
                                onBackPressed()
                            }
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        with(transitionScope) {
            BlurImageBackground(radius = 0, url = posterPath, modifier = modifier
                .padding(contentPadding)
                .fillMaxSize()
                .sharedElement(transitionScope.rememberSharedContentState(key = posterPath),
                    animatedVisibilityScope = animatedContentScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 500)
                    }
                )
            )
        }
        Box(modifier = modifier
            .padding(contentPadding)
            .fillMaxSize()) {
            if (uiState.showLoading) {
                LoadingScreen(modifier = modifier.height(48.dp).width(48.dp).padding(8.dp))
            } else {
                movie?.let {
                    MovieCardBottomView(modifier = modifier.align(Alignment.BottomCenter), movie = it)
                }
            }
        }
    }
}