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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private data class RefreshUiState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    private val _refreshState =
        MutableStateFlow(
            RefreshUiState()
        )

    val uiState: StateFlow<GameListUiState> =
        combine(
            repository.observeGames(),
            _refreshState
        ) { games, refreshState ->

            GameListUiState(
                games = games,
                isLoading = refreshState.isLoading,
                errorMessage = refreshState.errorMessage
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GameListUiState()
            )

    fun refreshGames() {

        viewModelScope.launch {

            _refreshState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {

                val result =
                    repository.refreshGames()

                if (!result.isSuccess) {

                    _refreshState.update {
                        it.copy(
                            errorMessage = result.errorMessage
                        )
                    }
                }

            } finally {

                _refreshState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

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

        val game =
            uiState.value.games
                .find { it.id == gameId }
                ?: return

        val updatedGame =
            game.copy(
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