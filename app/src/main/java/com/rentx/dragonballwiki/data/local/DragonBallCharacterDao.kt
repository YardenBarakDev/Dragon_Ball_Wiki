package com.rentx.dragonballwiki.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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

    @Query("SELECT createdAt FROM remote_keys WHERE page = :page LIMIT 1")
    fun getCreationTime(page: Int): Long?
}