package com.rentx.dragonballwiki.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rentx.core.data.local_db.DragonBallCharacterEntity
import com.rentx.core.data.local_db.RoomDBConstructor
import com.rentx.dragonballwiki.data.mappers.toDragonBallCharacterEntity
import com.rentx.dragonballwiki.model.DragonBallRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.math.max

@OptIn(ExperimentalPagingApi::class)
class DragonBallRemoteMediator(
    private val database: RoomDBConstructor,
    private val remoteDataSource: DragonBallRepository
) : RemoteMediator<Int, DragonBallCharacterEntity>() {

    private val characterDao = database.characterDao
    private val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
    private var page = 1

    override suspend fun initialize(): InitializeAction {
        return withContext(Dispatchers.IO) {
            val dateCreated = characterDao.getCharacterById(1)?.createdAt ?: 0
            if (System.currentTimeMillis() - dateCreated < cacheTimeout ) {
                InitializeAction.SKIP_INITIAL_REFRESH
            } else {
                InitializeAction.LAUNCH_INITIAL_REFRESH
            }
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DragonBallCharacterEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> page
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem != null) {
                        val charactersFromNextPage = characterDao.getCharactersByPageNumber(max(lastItem.page + 1, page))
                       if (charactersFromNextPage.isNotEmpty()){
                           return MediatorResult.Success(endOfPaginationReached = true)
                       }
                        page = lastItem.page + 1
                    }
                    page
                }
            }
            page+=1
            val characters = remoteDataSource.getAllCharacters(loadKey)
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAllCharacters()
                }
                characterDao.insertAllCharacters(characters.toDragonBallCharacterEntity(loadKey))
            }
            return MediatorResult.Success(endOfPaginationReached = characters.items.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}