package com.rentx.dragonballwiki.data.mappers

import com.rentx.dragonballwiki.data.local.DragonBallCharacterEntity
import com.rentx.dragonballwiki.data.network.dto.DragonBallCharactersResponse
import com.rentx.dragonballwiki.model.DragonBallCharacter

fun DragonBallCharactersResponse.toDragonBallCharacter(): List<DragonBallCharacter> {
    return this.items.map {
        DragonBallCharacter(
            name = it.name,
            ki = it.ki,
            maxKi = it.maxKi,
            race = it.race,
            gender = it.gender,
            description = it.description,
            image = it.image,
            affiliation = it.affiliation,
            deletedAt = it.deletedAt,
        )
    }
}

fun DragonBallCharacterEntity.toDragonBallCharacter(): DragonBallCharacter {
    return DragonBallCharacter(
        name = name,
        ki = ki,
        maxKi = maxKi,
        race = race,
        gender = gender,
        description = description,
        image = image,
        affiliation = affiliation,
        deletedAt = deletedAt,
        isFavorite = isFavorite
    )
}
