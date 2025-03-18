package com.rentx.dragonballwiki.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class DragonBallCharactersResponse(
    val items: List<Character>,
    val meta: Meta,
    val links: Links
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val deletedAt: String?
)

@Serializable
data class Meta(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int
)

@Serializable
data class Links(
    val first: String,
    val previous: String?,
    val next: String?,
    val last: String
)
