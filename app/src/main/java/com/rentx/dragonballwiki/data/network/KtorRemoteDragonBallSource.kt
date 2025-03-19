package com.rentx.dragonballwiki.data.network

import com.plcoding.bookpedia.core.data.safeCall
import com.plcoding.bookpedia.core.domain.DataError
import com.rentx.core.domain.Result
import com.rentx.dragonballwiki.data.network.dto.DragonBallCharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteDragonBallSource(private val httpClient: HttpClient) : RemoteDataSource {


    override suspend fun getAllCharacters(page: Int): DragonBallCharactersResponse {

        return httpClient.get("https://dragonball-api.com/api/characters?page=$page").body()
    }

    suspend fun getAllCharacters2(page: Int): Result<DragonBallCharactersResponse, DataError.Remote> {
        return safeCall {
            httpClient.get("https://dragonball-api.com/api/characters"){
                parameter("page", page)
            }
        }
    }
}