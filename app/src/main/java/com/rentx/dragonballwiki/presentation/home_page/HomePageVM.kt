package com.rentx.dragonballwiki.presentation.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rentx.core.data.local_db.RoomDBConstructor
import com.rentx.dragonballwiki.data.paging.DragonBallRemoteMediator
import com.rentx.dragonballwiki.model.DragonBallRepository

class HomePageVM(
    private val repository: DragonBallRepository,
    private val database: RoomDBConstructor,
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 1,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            database.characterDao.getAllCharacters()
        },
        remoteMediator = DragonBallRemoteMediator(database, repository)
    ).flow
        .cachedIn(viewModelScope)

}