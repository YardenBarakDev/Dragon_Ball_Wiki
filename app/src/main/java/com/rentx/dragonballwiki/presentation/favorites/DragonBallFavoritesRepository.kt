package com.rentx.dragonballwiki.presentation.favorites

import com.rentx.core.local_db.DragonBallCharacterEntity
import kotlinx.coroutines.flow.Flow

interface DragonBallFavoritesRepository {
    suspend fun addToFavorites(characterId: Int)
    suspend fun removeFromFavorites(characterId: Int)
    suspend fun getFavoriteCharacters(): Flow<List<DragonBallCharacterEntity>>
}