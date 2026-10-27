package com.example.tymkov.gametracker.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [
        GameEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}