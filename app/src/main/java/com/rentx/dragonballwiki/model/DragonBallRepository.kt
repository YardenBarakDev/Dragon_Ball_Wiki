package com.rentx.dragonballwiki.model

interface DragonBallRepository {
    suspend fun getAllCharacters(pageNumber: Int): List<DragonBallCharacter>

}