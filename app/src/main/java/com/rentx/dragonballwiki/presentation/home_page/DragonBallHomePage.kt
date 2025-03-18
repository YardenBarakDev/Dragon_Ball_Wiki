package com.rentx.dragonballwiki.presentation.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rentx.dragonballwiki.data.local.DragonBallCharacterEntity
import com.rentx.dragonballwiki.data.mappers.toDragonBallCharacter
import com.rentx.dragonballwiki.model.DragonBallCharacter
import com.rentx.dragonballwiki.presentation.components.DragonBallCharactersItem
import com.rentx.dragonballwiki.presentation.SelectedCharacterVM
import com.rentx.dragonballwiki.ui.theme.DBOrange

@Composable
fun DragonBallHomePage(
    modifier: Modifier,
    viewModel: HomePageVM,
    selectedCharacterVM: SelectedCharacterVM,
    onCharacterClick: (DragonBallCharacterEntity) -> Unit
) {
    val pagingData = viewModel.flow.collectAsLazyPagingItems()
    DragonBallHomePageImpl(modifier, pagingData = pagingData, onCharacterClick)
}

@Composable
private fun DragonBallHomePageImpl(
    modifier: Modifier,
    pagingData: LazyPagingItems<DragonBallCharacterEntity>,
    onCharacterClick: (DragonBallCharacterEntity) -> Unit
) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier
            .fillMaxSize()
            .background(DBOrange),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        items(pagingData.itemCount) { index ->
            pagingData[index]?.let {
                DragonBallCharactersItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCharacterClick(it) },
                    character = it.toDragonBallCharacter()
                )
            }
        }
    }
}