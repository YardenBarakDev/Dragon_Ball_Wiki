package com.rentx.dragonballwiki.presentation.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rentx.core.data.local_db.DragonBallCharacterEntity
import com.rentx.core.presentation.components.DragonBallCharactersItem
import com.rentx.dragonballwiki.ui.theme.DBBackground

@Composable
fun DragonBallHomePage(
    modifier: Modifier,
    viewModel: HomePageVM,
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
            .background(DBBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        items(pagingData.itemCount) { index ->
            pagingData[index]?.let {
                DragonBallCharactersItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCharacterClick(it) },
                    character = it
                )
            }
        }
    }
}