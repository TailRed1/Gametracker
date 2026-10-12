package com.example.tymkov.gametracker.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object GameListRoute : NavKey

@Serializable
data object AddGameRoute : NavKey

@Serializable
data class GameDetailsRoute(
    val gameId: Int
) : NavKey