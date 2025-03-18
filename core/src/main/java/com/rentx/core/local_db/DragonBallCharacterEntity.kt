package com.rentx.core.local_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DragonBallCharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val deletedAt: String?,
    val page: Int,
    val createdAt: Long = System.currentTimeMillis(),
    var isFavorite: Boolean = false
    )
