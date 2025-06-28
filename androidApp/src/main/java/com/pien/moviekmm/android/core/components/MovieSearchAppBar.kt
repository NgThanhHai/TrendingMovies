package com.pien.moviekmm.android.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pien.moviekmm.android.R
import com.pien.moviekmm.android.features.trendingmovies.conponents.MovieSearchBar

@Composable
fun MovieSearchAppBar(modifier: Modifier = Modifier,
    titleHeight: Dp = 50.dp,
    searchBarHeight: Dp = 60.dp,
    searchText: String = "",
    showReload: Boolean = false,
    defaultTitle: String = stringResource(R.string.str_trending_screen_title_default),
    searchTitle: String = stringResource(R.string.str_trending_screen_title_in_searching),
    onQueryChanged: (String) -> Unit) {
    Text(if(searchText.isNotEmpty()) searchTitle else defaultTitle,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(titleHeight))
    MovieSearchBar(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(15.dp))
        .clip(RoundedCornerShape(15.dp))
        .height(searchBarHeight),
        searchText = searchText,
        showReload = showReload,
        onQueryChange = { onQueryChanged(it) },
        onClearText = { onQueryChanged("") })
}