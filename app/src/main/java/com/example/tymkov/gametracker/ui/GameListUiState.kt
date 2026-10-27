package com.example.tymkov.gametracker.ui

import com.example.tymkov.gametracker.model.Game

data class GameListUiState(
    val games: List<Game> = emptyList()
)