package com.rentx.dragonballwiki.data.network

import com.rentx.dragonballwiki.data.network.dto.DragonBallCharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorRemoteDragonBallSource(private val httpClient: HttpClient) : RemoteDataSource {


    override suspend fun getAllCharacters(page: Int): DragonBallCharactersResponse {
        return httpClient.get("https://dragonball-api.com/api/characters?page=$page").body()
    }
}