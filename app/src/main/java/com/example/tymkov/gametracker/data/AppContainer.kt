package com.example.tymkov.gametracker.data

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.tymkov.gametracker.data.local.AppDatabase

class AppContainer(
    context: Context
) {

    private val database: AppDatabase =
        Room.databaseBuilder<AppDatabase>(
            context = context.applicationContext,
            name = "game_tracker.db"
        )
            .setDriver(
                AndroidSQLiteDriver()
            )
            .build()

    val gameRepository: GameRepository =
        GameRepository(
            gameDao = database.gameDao()
        )
}