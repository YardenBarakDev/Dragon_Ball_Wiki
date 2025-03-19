package com.rentx.core.data.local_db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DragonBallCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<DragonBallCharacterEntity>)

    @Query("SELECT * FROM DragonBallCharacterEntity")
    fun getAllCharacters(): PagingSource<Int, DragonBallCharacterEntity>

    @Query("SELECT * FROM DragonBallCharacterEntity WHERE page = :page")
    fun getCharacterListPage(page: Int): PagingSource<Int, DragonBallCharacterEntity>

    @Query("SELECT * FROM DragonBallCharacterEntity WHERE page = :page")
    suspend fun getCharactersByPageNumber(page: Int): List<DragonBallCharacterEntity>

    @Query("SELECT * FROM DragonBallCharacterEntity WHERE id = :id")
    suspend fun getCharacterById(id: Int): DragonBallCharacterEntity?

    @Query("DELETE FROM DragonBallCharacterEntity")
    suspend fun clearAllCharacters()

    @Query("SELECT COUNT(*) FROM DragonBallCharacterEntity")
    suspend fun count(): Int

    @Query("UPDATE DragonBallCharacterEntity SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM DragonBallCharacterEntity WHERE isFavorite = 1")
    fun getFavoriteCharacters(): Flow<List<DragonBallCharacterEntity>>

}