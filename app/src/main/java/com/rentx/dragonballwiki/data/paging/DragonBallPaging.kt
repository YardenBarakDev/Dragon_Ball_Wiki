package com.rentx.dragonballwiki.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rentx.dragonballwiki.model.DragonBallCharacter
import com.rentx.dragonballwiki.model.DragonBallRepository
import io.ktor.client.plugins.HttpRequestTimeoutException
import java.io.IOException

class DragonBallPaging(
    private val backend: DragonBallRepository,
) : PagingSource<Int, DragonBallCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DragonBallCharacter> {
        return try {
            val currentPage = params.key ?: 1
            val response = backend.getAllCharacters(currentPage)
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpRequestTimeoutException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DragonBallCharacter>): Int? {
       val a = state.anchorPosition?.let { anchorPosition ->
           val anchorPage = state.closestPageToPosition(anchorPosition)
           anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
       }
        return a
    }
}