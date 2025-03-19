package com.rentx.core.presentation.components

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
            color = Color.White
        )
        ){
            append("$q: ")
        }
        withStyle(style = SpanStyle(
            fontSize = 22.sp,
            color = Color(0xFFFBC02C)
        )
        ) {
            append(a)
        }
    }
}