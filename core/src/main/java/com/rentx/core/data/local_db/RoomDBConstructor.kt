package com.rentx.core.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DragonBallCharacterEntity::class], version = 1)
abstract class RoomDBConstructor: RoomDatabase() {
    abstract val characterDao: DragonBallCharacterDao
}