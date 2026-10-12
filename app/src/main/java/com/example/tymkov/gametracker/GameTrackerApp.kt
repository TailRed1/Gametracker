package com.example.tymkov.gametracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.tymkov.gametracker.model.Game
import com.example.tymkov.gametracker.navigation.AddGameRoute
import com.example.tymkov.gametracker.navigation.GameDetailsRoute
import com.example.tymkov.gametracker.navigation.GameListRoute
import com.example.tymkov.gametracker.ui.screens.AddGameScreen
import com.example.tymkov.gametracker.ui.screens.GameDetailsScreen
import com.example.tymkov.gametracker.ui.screens.GameListScreen

@Composable
fun GameTrackerApp() {

    var games by remember {
        mutableStateOf(
            listOf(
                Game(
                    id = 1,
                    title = "Cyberpunk 2077",
                    genre = "RPG",
                    description = "Відкрита рольова гра у футуристичному світі Night City.",
                    isCompleted = true
                ),

                Game(
                    id = 2,
                    title = "The Witcher 3",
                    genre = "RPG",
                    description = "Пригодницька рольова гра у відкритому світі.",
                    isCompleted = true
                ),

                Game(
                    id = 3,
                    title = "Grand Theft Auto V",
                    genre = "Action",
                    description = null,
                    isCompleted = false
                )
            )
        )
    }

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

                        val nextId =
                            (games.maxOfOrNull { it.id } ?: 0) + 1

                        val newGame = Game(
                            id = nextId,
                            title = title,
                            genre = genre,
                            description = description,
                            isCompleted = isCompleted
                        )

                        games = games + newGame

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

                        games = toggleGameCompleted(
                            games = games,
                            gameId = route.gameId
                        )
                    }
                )
            }
        }
    )
}

private fun toggleGameCompleted(
    games: List<Game>,
    gameId: Int
): List<Game> {

    return games.map { game ->

        if (game.id == gameId) {

            game.copy(
                isCompleted = !game.isCompleted
            )

        } else {

            game
        }
    }
}