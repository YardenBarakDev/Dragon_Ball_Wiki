package com.rentx.dragonballwiki.data.mappers

import com.rentx.dragonballwiki.data.local.DragonBallCharacterEntity
import com.rentx.dragonballwiki.data.network.dto.DragonBallCharactersResponse

fun DragonBallCharactersResponse.toDragonBallCharacterEntity(page: Int): List<DragonBallCharacterEntity> {
    return this.items.map {
        DragonBallCharacterEntity(
            id = it.id,
            name = it.name,
            ki = it.ki,
            maxKi = it.maxKi,
            race = it.race,
            gender = it.gender,
            description = it.description,
            image = it.image,
            affiliation = it.affiliation,
            deletedAt = it.deletedAt,
            page = page
        )
    }
}