package com.pien.moviekmm.android.features.trendingmovies.conponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieSearchBar(modifier: Modifier = Modifier,
    searchText: String,
    onQueryChange: (String) -> Unit,
    onClearText: () -> Unit) {

    OutlinedTextField(modifier = modifier,
        value = searchText,
        placeholder = {
            Text(
                text = "Search your movie...",
                fontSize = 15.sp)
         },
        onValueChange = { onQueryChange(it) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        shape = RoundedCornerShape(15.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        ),
        trailingIcon = {
            if(searchText.isNotEmpty()) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                            onClearText.invoke()
                        }
                )
            }
        }
    )
}

@Preview
@Composable
fun MovieSearchBarPreview() {
    MovieSearchBar(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .height(70.dp),
        searchText = "",
        onQueryChange = {},
        onClearText = {})
}