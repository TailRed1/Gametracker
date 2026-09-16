package com.example.tymkov.gametracker.model

data class Game(
    val id: Int,
    val title: String,
    val genre: String,
    val description: String?,
    var isCompleted: Boolean
)