package com.rentx.dragonballwiki.model

import com.rentx.dragonballwiki.data.network.dto.DragonBallCharactersResponse

interface DragonBallRepository {
    suspend fun getAllCharacters(pageNumber: Int): DragonBallCharactersResponse

}