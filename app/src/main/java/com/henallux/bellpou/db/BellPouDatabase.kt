package com.henallux.bellpou.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LoggedUserEntity::class],
    version = 1
)
abstract class BellPouDatabase: RoomDatabase() {
    abstract fun bellPouDao(): BellPouDao
}