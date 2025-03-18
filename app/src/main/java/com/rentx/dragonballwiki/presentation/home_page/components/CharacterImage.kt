package com.rentx.dragonballwiki.presentation.home_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import com.rentx.dragonballwiki.R

@Composable
fun CharacterImage(modifier: Modifier = Modifier, state: AsyncImagePainter. State) {
    when(state){
        AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading -> {
            CircularProgressIndicator(modifier)
        }
        is AsyncImagePainter.State.Error -> {
            Image(
                modifier = modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.dragon_ball_logo),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        is AsyncImagePainter.State.Success -> {
            Image(
                modifier = modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                painter = state.painter,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}