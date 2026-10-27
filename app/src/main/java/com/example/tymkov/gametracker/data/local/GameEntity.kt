package com.example.tymkov.gametracker.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "games"
)
data class GameEntity(

    @PrimaryKey(
        autoGenerate = true
    )
    val id: Int = 0,

    val title: String,

    val genre: String,

    val description: String?,

    val isCompleted: Boolean
)