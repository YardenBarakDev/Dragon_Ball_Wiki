package com.rentx.dragonballwiki.presentation.home_page.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun sectionText(q: String, a: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 24.sp,
            color = Color(0xFFFB4813)
        )
        ){
            append("$q: ")
        }
        withStyle(style = SpanStyle(
            fontSize = 22.sp,
            color = Color.Black
        )
        ) {
            append(a)
        }
    }
}