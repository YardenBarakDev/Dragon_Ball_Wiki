package com.rentx.dragonballwiki.data.network

import com.rentx.dragonballwiki.data.network.dto.DragonBallCharactersResponse

interface RemoteDataSource {
    suspend fun getAllCharacters(page: Int): DragonBallCharactersResponse
}