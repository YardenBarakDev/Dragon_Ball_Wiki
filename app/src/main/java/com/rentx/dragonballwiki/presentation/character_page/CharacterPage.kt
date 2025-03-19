package com.rentx.dragonballwiki.presentation.character_page

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.rememberAsyncImagePainter
import com.rentx.dragonballwiki.R
import com.rentx.core.data.local_db.DragonBallCharacterEntity
import com.rentx.core.presentation.components.CharacterImage
import com.rentx.dragonballwiki.presentation.SelectedCharacterVM
import com.rentx.core.presentation.components.CharactersDetailsSection
import com.rentx.dragonballwiki.ui.theme.DBBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CharacterPage(modifier: Modifier, viewModel: SelectedCharacterVM, onBackClick: () -> Unit) {
    val character = viewModel.character
    CharacterPageImpl(modifier, character) {
        viewModel.updateCharacter(isFavorite = character?.isFavorite ?: false)
        onBackClick.invoke()
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterPageImpl(
    modifier: Modifier,
    character: DragonBallCharacterEntity?,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DBBackground)
            .verticalScroll(rememberScrollState()),
    ) {
        val previewHandler = AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FavoriteButton(
                initialFavorite = character?.isFavorite ?: false,
                onFavoriteChanged = { newValue -> character?.isFavorite = newValue }
            )

            Image(
                modifier = Modifier.clickable { onBackClick.invoke() },
                painter = painterResource(R.drawable.baseline_close_24),
                contentDescription = null
            )
        }

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
        Spacer(modifier = Modifier.height(12.dp))
        CharactersDetailsSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            character = character!!
        )
        Text(
            text = "Description:",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Text(
            text = character.description,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            color = Color(0xFFFBC02C),
            modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp)
        )
    }
}

@Composable
fun FavoriteButton(
    initialFavorite: Boolean,
    onFavoriteChanged: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(initialFavorite) }
    var animate by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (animate) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300, easing = EaseInOut),
        label = "Favorite Pulse"
    )
    Image(
        painter = painterResource(if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24),
        contentDescription = null,
        modifier = Modifier
            .scale(scale)
            .clickable {
                isFavorite = !isFavorite
                animate = true
                onFavoriteChanged(isFavorite)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(300)
                    animate = false
                }
            }
    )
}


@Preview
@Composable
private fun CharacterPagePrev() {
    CharacterPageImpl(
        Modifier,
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
            isFavorite = false,
            id = 1,
            page = 2,
            createdAt = 111L
        ),
        onBackClick = {}
    )
}