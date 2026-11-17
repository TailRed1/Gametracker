package com.example.tymkov.gametracker.data

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.tymkov.gametracker.data.local.AppDatabase
import com.example.tymkov.gametracker.data.remote.GameApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

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

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()

    private val gameApiService: GameApiService =
        retrofit.create(
            GameApiService::class.java
        )

    val gameRepository: GameRepository =
        GameRepository(
            gameDao = database.gameDao(),
            gameApiService = gameApiService
        )

    companion object {
        private const val BASE_URL =
            "https://www.freetogame.com/api/"
    }
}