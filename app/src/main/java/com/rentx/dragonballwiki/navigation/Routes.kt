package com.rentx.dragonballwiki.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object DragonBallHomePage : Route

    @Serializable
    data object CharacterDetail: Route

    @Serializable
    data object FavoriteScreen: Route
}