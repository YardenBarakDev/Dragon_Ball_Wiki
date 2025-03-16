package com.rentx.dragonballwiki.presentation.home_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePainter
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.rememberAsyncImagePainter
import com.rentx.dragonballwiki.R
import com.rentx.dragonballwiki.model.DragonBallCharacter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DragonBallCharactersItem(
    modifier: Modifier = Modifier,
    character: DragonBallCharacter
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        val previewHandler = AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            val painter = rememberAsyncImagePainter(character.image)
            val state by painter.state.collectAsState()
            CharacterImage(state = state, modifier = Modifier.padding(8.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 12.dp)
        ) {

            Text(sectionText("Name", character.name))
            Text(sectionText("Race", character.race))
            Text(sectionText("Affiliation", character.affiliation))
            Text(sectionText("Gender", character.gender))
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun sectionText(q: String, a: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 24.sp,
            color = Color(0xFFFB4813)
        )){
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

@Preview
@Composable
private fun DragonBallCharactersItemPrev() {
    DragonBallCharactersItem(
        character = DragonBallCharacter(
            id = 1,
            name = "Guko",
            ki = "5000",
            maxKi = "10000000000",
            race = "Sayin",
            gender = "Male",
            description = "asfhaksjfhas hfjkashfa sjksfhkja sflsfoasf uiasgfuiasg fuiasg fuiagsfuiasgf uiagsfiu af",
            image = "",
            affiliation = "Z warrior",
            deletedAt = null
        )
    )
}