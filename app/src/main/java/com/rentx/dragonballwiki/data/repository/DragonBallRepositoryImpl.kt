package com.rentx.dragonballwiki.data.repository

import com.rentx.dragonballwiki.data.network.RemoteDataSource
import com.rentx.dragonballwiki.data.network.dto.DragonBallCharactersResponse
import com.rentx.dragonballwiki.model.DragonBallRepository

class DragonBallRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DragonBallRepository {


    override suspend fun getAllCharacters(pageNumber: Int): DragonBallCharactersResponse {
        return remoteDataSource.getAllCharacters(pageNumber)
    }
}