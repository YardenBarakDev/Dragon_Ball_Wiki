package com.rentx.dragonballwiki.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class DragonBallRemoteKeyEntity(
    @PrimaryKey val id: Int,
    val page: Int,
    val createdAt: Long = System.currentTimeMillis(),
)