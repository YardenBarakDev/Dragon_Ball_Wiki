package com.rentx.dragonballwiki.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentx.dragonballwiki.R

@Composable
fun MainHeader(
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),)
    {
        Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(R.drawable.dragonball_z),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterStart)
                .clickable { onFavoriteClick.invoke() },
            painter = painterResource(R.drawable.baseline_favorite_24),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun MainHeaderPrev() {
    MainHeader{}
}