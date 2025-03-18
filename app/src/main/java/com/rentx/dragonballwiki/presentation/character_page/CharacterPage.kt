package com.rentx.dragonballwiki.presentation.character_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.rememberAsyncImagePainter
import com.rentx.dragonballwiki.model.DragonBallCharacter
import com.rentx.dragonballwiki.presentation.home_page.components.CharacterImage
import com.rentx.dragonballwiki.presentation.home_page.components.SelectedCharacterVM
import com.rentx.dragonballwiki.presentation.home_page.components.sectionText

@Composable
fun CharacterPage(modifier: Modifier, viewModel: SelectedCharacterVM, onBackClick: () -> Unit) {
    val chosenCharacter by viewModel.chosenCharacter.collectAsState()
    CharacterPageImpl(modifier, chosenCharacter, onBackClick)
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterPageImpl(modifier: Modifier, character: DragonBallCharacter?, onBackClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { onBackClick.invoke() }
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    )
    {
        val previewHandler = AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }
        Image(
            painter = painterResource()
        )
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            val painter = rememberAsyncImagePainter(character?.image)
            val state by painter.state.collectAsState()
            CharacterImage(
                state = state,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
            )
        }
        Text(sectionText("Name", character?.name ?: ""))
        Text(sectionText("Race", character?.race ?: ""))
        Text(sectionText("Affiliation", character?.affiliation ?: ""))
        Text(sectionText("Gender", character?.gender ?: ""))
        Text(sectionText("Ki", character?.ki ?: ""))
        Text(sectionText("Max ki", character?.maxKi ?: ""))
        Text(sectionText("Description", character?.description ?: ""))
    }
}

@Preview
@Composable
private fun CharacterPagePrev() {
    CharacterPageImpl(
        Modifier,
        character = DragonBallCharacter(
            name = "Guko",
            ki = "5000",
            maxKi = "10000000000",
            race = "Sayin",
            gender = "Male",
            description = "asfhaksjfhas hfjkashfa sjksfhkja sflsfoasf uiasgfuiasg fuiasg fuiagsfuiasgf uiagsfiu af",
            image = "",
            affiliation = "Z warrior",
            deletedAt = null
        ),
        onBackClick = {}
    )
}