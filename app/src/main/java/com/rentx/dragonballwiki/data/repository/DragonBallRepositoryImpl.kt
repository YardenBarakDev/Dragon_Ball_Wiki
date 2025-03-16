package com.rentx.dragonballwiki.data.repository

import com.rentx.dragonballwiki.data.mappers.toDragonBallCharacter
import com.rentx.dragonballwiki.data.network.RemoteDataSource
import com.rentx.dragonballwiki.model.DragonBallCharacter
import com.rentx.dragonballwiki.model.DragonBallRepository

class DragonBallRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DragonBallRepository {


    override suspend fun getAllCharacters(pageNumber: Int): List<DragonBallCharacter> {
        return remoteDataSource.getAllCharacters(pageNumber).toDragonBallCharacter()
    }
}