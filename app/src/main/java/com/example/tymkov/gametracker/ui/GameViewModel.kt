package com.example.tymkov.gametracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tymkov.gametracker.GameTrackerApplication
import com.example.tymkov.gametracker.data.GameRepository
import com.example.tymkov.gametracker.model.Game
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    val uiState: StateFlow<GameListUiState> =
        repository.observeGames()
            .map { games ->
                GameListUiState(
                    games = games
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GameListUiState()
            )

    fun addGame(
        title: String,
        genre: String,
        description: String?,
        isCompleted: Boolean
    ) {
        val game = Game(
            id = 0,
            title = title,
            genre = genre,
            description = description,
            isCompleted = isCompleted
        )

        viewModelScope.launch {
            repository.addGame(game)
        }
    }

    fun toggleGameCompleted(
        gameId: Int
    ) {
        val game = uiState.value.games
            .find { it.id == gameId }
            ?: return

        val updatedGame = game.copy(
            isCompleted = !game.isCompleted
        )

        viewModelScope.launch {
            repository.updateGame(updatedGame)
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {

                initializer {

                    val application =
                        this[APPLICATION_KEY]
                                as GameTrackerApplication

                    val repository =
                        application.container.gameRepository

                    GameViewModel(
                        repository = repository
                    )
                }
            }
    }
}