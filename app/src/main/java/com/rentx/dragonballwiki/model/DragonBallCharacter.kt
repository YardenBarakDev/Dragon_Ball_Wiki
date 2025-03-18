package com.rentx.dragonballwiki.model

import kotlinx.serialization.Serializable

@Serializable
data class DragonBallCharacter(
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val deletedAt: String?,
    val isFavorite: Boolean = false
)
