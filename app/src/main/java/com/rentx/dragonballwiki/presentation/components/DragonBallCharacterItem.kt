package com.rentx.dragonballwiki.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.rememberAsyncImagePainter
import com.rentx.core.local_db.DragonBallCharacterEntity
import com.rentx.dragonballwiki.ui.theme.DBSection

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DragonBallCharactersItem(
    modifier: Modifier = Modifier,
    character: DragonBallCharacterEntity
) {
    Column(
        modifier = Modifier
            .background(DBSection, shape = RoundedCornerShape(24.dp))
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
            CharacterImage(
                state = state,
                modifier = Modifier.clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
            )
        }

        CharactersDetailsSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp), character
        )
    }
}

@Preview
@Composable
private fun DragonBallCharactersItemPrev() {
    DragonBallCharactersItem(
        character = DragonBallCharacterEntity(
            name = "Guko",
            ki = "5000",
            maxKi = "10000000000",
            race = "Sayin",
            gender = "Male",
            description = "asfhaksjfhas hfjkashfa sjksfhkja sflsfoasf uiasgfuiasg fuiasg fuiagsfuiasgf uiagsfiu af",
            image = "",
            affiliation = "Z warrior",
            deletedAt = null,
            id = 1,
            page = 1,
            createdAt = 1,
            isFavorite = false
        )
    )
}