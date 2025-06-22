package com.pien.moviekmm.android.features.moviedetails.presentation

import NoConnectionScreen
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pien.moviekmm.android.core.components.IndeterminateCircularIndicator
import com.pien.moviekmm.android.core.components.ImagePosterView
import com.pien.moviekmm.android.features.moviedetails.components.MovieCardBottomView
import com.pien.moviekmm.features.moviedetails.MovieDetailUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    uiState: MovieDetailUIState,
    posterPath: String,
    modifier: Modifier,
    onBackPressed: () -> Unit
) {
    val movie = uiState.movieDetail
    val context = LocalContext.current
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
                    Text(movie?.title ?: "")
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable {
                                onBackPressed()
                            }
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = modifier
            .padding(contentPadding)) {
            ImagePosterView(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                urlPath = posterPath
            )
        }
        Box(modifier = modifier.padding(contentPadding)
            .fillMaxSize()) {
            if (uiState.showLoading) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    IndeterminateCircularIndicator(
                        Modifier
                            .height(48.dp)
                            .width(48.dp)
                            .padding(8.dp)
                    )
                }
            } else
                if (uiState.error.isNotEmpty()) {
                    LaunchedEffect(uiState.error) {
                        Toast.makeText(context, uiState.error, Toast.LENGTH_LONG).show()
                    }
                } else {
                    movie?.let {
                        MovieCardBottomView(modifier = modifier.align(Alignment.BottomCenter), movie = it)
                    }
                }
        }
    }
}