package com.rentx.dragonballwiki.presentation.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rentx.dragonballwiki.data.paging.DragonBallPaging
import com.rentx.dragonballwiki.model.DragonBallCharacter
import com.rentx.dragonballwiki.model.DragonBallRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomePageVM(private val repository: DragonBallRepository) : ViewModel() {

    private val _charactersList = MutableStateFlow<List<DragonBallCharacter>>(emptyList())
    val charactersList = _charactersList.asStateFlow()

    val flow = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 10
        )
    ) {
        DragonBallPaging(repository)
    }.flow
        .cachedIn(viewModelScope)


}