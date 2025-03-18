package com.rentx.dragonballwiki.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DragonBallRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: DragonBallRemoteKeyEntity)

    @Query("SELECT * FROM remote_keys WHERE page = :page")
    suspend fun getRemoteKey(page: Int): DragonBallRemoteKeyEntity?

    @Query("DELETE FROM remote_keys WHERE page = :page")
    suspend fun deleteByQuery(page: Int)

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()

    @Query("SELECT createdAt FROM remote_keys WHERE page = :page")
    fun getCreationTime(page: Int): Long?

}