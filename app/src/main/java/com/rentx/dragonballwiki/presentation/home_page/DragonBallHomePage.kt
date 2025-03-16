package com.rentx.dragonballwiki.presentation.home_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rentx.dragonballwiki.R
import com.rentx.dragonballwiki.model.DragonBallCharacter
import org.koin.androidx.compose.koinViewModel

@Composable
fun DragonBallHomePage(
    viewModel: HomePageVM = koinViewModel()
) {
    val characters by viewModel.charactersList.collectAsState()
    val pagingData = viewModel.flow.collectAsLazyPagingItems()
    DragonBallHomePageImpl(pagingData = pagingData)
}


@Composable
private fun DragonBallHomePageImpl(pagingData: LazyPagingItems<DragonBallCharacter>) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                horizontalArrangement = Arrangement.Center
            )
            {
                Image(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    painter = painterResource(R.drawable.dragonball_z),
                    contentDescription = null,
                )
            }
        },
        content = { padding ->
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFFA4714)),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                items(pagingData.itemCount) { index ->
                    pagingData[index]?.let {
                        DragonBallCharactersItem(
                            modifier = Modifier
                                .fillMaxWidth(),
                            character = it
                        )
                    }
                }
            }
        })
}


@Preview
@Composable
private fun DragonBallHomePagePrev() {

}