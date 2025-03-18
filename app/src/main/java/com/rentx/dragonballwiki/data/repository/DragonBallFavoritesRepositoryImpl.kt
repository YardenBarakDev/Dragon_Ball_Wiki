package com.rentx.dragonballwiki.data.repository

import com.rentx.core.local_db.RoomDBConstructor
import com.rentx.dragonballwiki.presentation.favorites.DragonBallFavoritesRepository

class DragonBallFavoritesRepositoryImpl(
    private val database: RoomDBConstructor,
): DragonBallFavoritesRepository {

    override suspend fun addToFavorites(characterId: Int) {
        database.characterDao.updateFavoriteStatus(characterId, true)
    }

    override suspend fun removeFromFavorites(characterId: Int) {
        database.characterDao.updateFavoriteStatus(characterId, false)
    }

    override suspend fun getFavoriteCharacters() = database.characterDao.getFavoriteCharacters()

}