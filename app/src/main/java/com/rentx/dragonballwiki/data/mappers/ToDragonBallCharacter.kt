package com.rentx.dragonballwiki.data.mappers

import com.rentx.dragonballwiki.data.dto.DragonBallCharactersResponse
import com.rentx.dragonballwiki.model.DragonBallCharacter

fun DragonBallCharactersResponse.toDragonBallCharacter(): List<DragonBallCharacter> {
    return this.items.map {
        DragonBallCharacter(
            id = it.id,
            name = it.name,
            ki = it.ki,
            maxKi = it.maxKi,
            race = it.race,
            gender = it.gender,
            description = it.description,
            image = it.image,
            affiliation = it.affiliation,
            deletedAt = it.deletedAt
        )
    }
}