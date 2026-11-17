package com.example.tymkov.gametracker.data

import com.example.tymkov.gametracker.data.local.GameDao
import com.example.tymkov.gametracker.data.remote.GameApiService
import com.example.tymkov.gametracker.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException

class GameRepository(
    private val gameDao: GameDao,
    private val gameApiService: GameApiService
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

    suspend fun refreshGames(): RefreshResult {

        return try {

            val gamesFromNetwork =
                gameApiService.getGames()

            val entities =
                gamesFromNetwork.map { dto ->
                    dto
                        .toGame()
                        .toEntity()
                }

            gameDao.upsertAll(
                games = entities
            )

            RefreshResult(
                isSuccess = true
            )

        } catch (e: IOException) {

            RefreshResult(
                isSuccess = false,
                errorMessage = "Немає з'єднання з мережею"
            )

        } catch (e: HttpException) {

            RefreshResult(
                isSuccess = false,
                errorMessage = "Сервер повернув помилку: ${e.code()}"
            )

        } catch (e: SerializationException) {

            RefreshResult(
                isSuccess = false,
                errorMessage = "Некоректна відповідь сервера"
            )
        }
    }
}