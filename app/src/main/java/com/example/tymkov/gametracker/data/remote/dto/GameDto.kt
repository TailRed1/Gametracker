package com.example.tymkov.gametracker.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.room3.Upsert

@Serializable
data class GameDto(
    val id: Int,
    val title: String,
    val genre: String,

    @SerialName("short_description")
    val shortDescription: String
)