package com.henallux.bellpou.db

import androidx.room.Room
import com.henallux.bellpou.App

class BellPouDBImpl {
    companion object {
        private val db = Room.databaseBuilder(
            App.applicationContext(),
            BellPouDatabase::class.java,
            "BellPouDB"
        ).build()

        fun getBellPouDao(): BellPouDao {
            return db.bellPouDao()
        }
    }
}