package com.pien.moviekmm.android.features.moviedetails.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit

const val tag = "URL_PATH"
@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = Color.LightGray,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlinks: List<String> = listOf(),
    fontSize: TextUnit = TextUnit.Unspecified,
    titleColor: Color = Color.Black
) {

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = fontSize,
                fontWeight = linkTextFontWeight,
                color = titleColor
            )
        ) {
            append(fullText)
        }
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = tag,
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium,
        onClick = {
            annotatedString.getStringAnnotations(tag, it, it).firstOrNull()?.let { link ->
                uriHandler.openUri(link.item)
            }
        }
    )
}