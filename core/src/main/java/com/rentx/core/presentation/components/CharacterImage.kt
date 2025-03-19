package com.rentx.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import com.rentx.core.R

@Composable
fun CharacterImage(modifier: Modifier = Modifier, state: AsyncImagePainter.State) {
    Box(modifier = modifier.paint(painterResource(R.drawable.image_background))){
        when (state) {
            AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(modifier)
            }

            is AsyncImagePainter.State.Error -> {
                Image(
                    modifier = modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    painter = painterResource(R.drawable.dragon_ball_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            is AsyncImagePainter.State.Success -> {
                Image(
                    modifier = modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    painter = state.painter,
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}