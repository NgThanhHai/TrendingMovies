package com.pien.moviekmm.android.features.moviedetails.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionText(modifier: Modifier,
    title: String,
    description: String) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        ) {
            append(title)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        ) {
            append(description)
        }
    }
    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier
    )
}