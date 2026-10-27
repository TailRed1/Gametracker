package com.example.tymkov.gametracker.data

import com.example.tymkov.gametracker.data.local.GameDao
import com.example.tymkov.gametracker.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val gameDao: GameDao
) {

    fun observeGames(): Flow<List<Game>> =
        gameDao.observeAll()
            .map { entities ->
                entities.map { entity ->
                    entity.toGame()
                }
            }

    suspend fun addGame(
        game: Game
    ) {
        gameDao.insert(
            game.toEntity()
        )
    }

    suspend fun updateGame(
        game: Game
    ) {
        gameDao.update(
            game.toEntity()
        )
    }
}