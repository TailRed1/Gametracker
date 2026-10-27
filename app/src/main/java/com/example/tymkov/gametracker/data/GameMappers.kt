package com.example.tymkov.gametracker.data

import com.example.tymkov.gametracker.data.local.GameEntity
import com.example.tymkov.gametracker.model.Game

fun GameEntity.toGame(): Game =
    Game(
        id = id,
        title = title,
        genre = genre,
        description = description,
        isCompleted = isCompleted
    )

fun Game.toEntity(): GameEntity =
    GameEntity(
        id = id,
        title = title,
        genre = genre,
        description = description,
        isCompleted = isCompleted
    )