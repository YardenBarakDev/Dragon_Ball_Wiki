package com.rentx.dragonballwiki.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentx.core.local_db.DragonBallCharacterEntity
import com.rentx.dragonballwiki.data.mappers.toDragonBallCharacter
import com.rentx.dragonballwiki.presentation.FavoritesCharactersVM
import com.rentx.dragonballwiki.presentation.components.DragonBallCharactersItem
import com.rentx.dragonballwiki.ui.theme.DBBackground
import com.rentx.dragonballwiki.ui.theme.DBOrange

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier, viewModel: FavoritesCharactersVM) {
    val favoriteList = viewModel.favoriteCharacters.collectAsState().value
    FavoritesScreenImpl(modifier = modifier, favoriteList = favoriteList)
}

@Composable
fun FavoritesScreenImpl(
    modifier: Modifier = Modifier,
    favoriteList: List<DragonBallCharacterEntity>
) {
    LazyVerticalGrid(
        state = rememberLazyGridState(),
        modifier = modifier
            .fillMaxSize()
            .background(DBBackground),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteList){
            DragonBallCharactersItem(character = it)
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPrev() {
    FavoritesScreenImpl(Modifier, listOf())
}