package com.example.tymkov.gametracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.tymkov.gametracker.navigation.AddGameRoute
import com.example.tymkov.gametracker.navigation.GameDetailsRoute
import com.example.tymkov.gametracker.navigation.GameListRoute
import com.example.tymkov.gametracker.ui.GameViewModel
import com.example.tymkov.gametracker.ui.screens.AddGameScreen
import com.example.tymkov.gametracker.ui.screens.GameDetailsScreen
import com.example.tymkov.gametracker.ui.screens.GameListScreen

@Composable
fun GameTrackerApp(
    gameViewModel: GameViewModel = viewModel(
        factory = GameViewModel.Factory
    )
) {

    val uiState by gameViewModel.uiState.collectAsStateWithLifecycle()

    val games = uiState.games

    val backStack = rememberNavBackStack(
        GameListRoute
    )

    NavDisplay(
        backStack = backStack,

        onBack = {
            backStack.removeLastOrNull()
        },

        entryProvider = entryProvider {

            entry<GameListRoute> {

                GameListScreen(
                    games = games,

                    onGameClick = { gameId ->

                        backStack.add(
                            GameDetailsRoute(
                                gameId = gameId
                            )
                        )
                    },

                    onAddGame = {

                        backStack.add(
                            AddGameRoute
                        )
                    }
                )
            }

            entry<AddGameRoute> {

                AddGameScreen(

                    onCancel = {
                        backStack.removeLastOrNull()
                    },

                    onAddGame = {
                            title,
                            genre,
                            description,
                            isCompleted ->

                        gameViewModel.addGame(
                            title = title,
                            genre = genre,
                            description = description,
                            isCompleted = isCompleted
                        )

                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<GameDetailsRoute> { route ->

                val game = games.find {
                    it.id == route.gameId
                }

                GameDetailsScreen(
                    game = game,

                    onBack = {
                        backStack.removeLastOrNull()
                    },

                    onToggleCompleted = {

                        gameViewModel.toggleGameCompleted(
                            gameId = route.gameId
                        )
                    }
                )
            }
        }
    )
}