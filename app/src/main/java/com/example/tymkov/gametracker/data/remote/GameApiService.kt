package com.example.tymkov.gametracker.data.remote

import com.example.tymkov.gametracker.data.remote.dto.GameDto
import retrofit2.http.GET

interface GameApiService {

    @GET("games")
    suspend fun getGames(): List<GameDto>
}