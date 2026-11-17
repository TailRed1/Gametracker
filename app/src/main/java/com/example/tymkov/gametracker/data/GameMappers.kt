package com.example.tymkov.gametracker.data

import com.example.tymkov.gametracker.data.local.GameEntity
import com.example.tymkov.gametracker.model.Game
import com.example.tymkov.gametracker.data.remote.dto.GameDto

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

fun GameDto.toGame(): Game =
    Game(
        id = id,
        title = title,
        genre = genre,
        description = shortDescription,
        isCompleted = false
    )