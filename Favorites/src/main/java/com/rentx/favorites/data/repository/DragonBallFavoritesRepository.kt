package com.rentx.favorites.data.repository

import com.rentx.core.data.local_db.DragonBallCharacterEntity
import kotlinx.coroutines.flow.Flow

interface DragonBallFavoritesRepository {
    suspend fun addToFavorites(characterId: Int)
    suspend fun removeFromFavorites(characterId: Int)
    suspend fun getFavoriteCharacters(): Flow<List<DragonBallCharacterEntity>>
}