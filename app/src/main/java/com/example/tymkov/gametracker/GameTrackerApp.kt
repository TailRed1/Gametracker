package com.example.tymkov.gametracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.tymkov.gametracker.model.Game
import com.example.tymkov.gametracker.ui.screens.AddGameForm
import com.example.tymkov.gametracker.ui.screens.GameList

@OptIn(ExperimentalMaterial3Api::class)
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

    var showAddForm by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (showAddForm) {
                            stringResource(R.string.add_game_title)
                        } else {
                            stringResource(R.string.games_title)
                        }
                    )
                }
            )
        },

        floatingActionButton = {
            if (!showAddForm) {
                FloatingActionButton(
                    onClick = {
                        showAddForm = true
                    }
                ) {
                    Text("+")
                }
            }
        }

    ) { innerPadding ->

        if (showAddForm) {

            AddGameForm(
                modifier = Modifier.padding(innerPadding),

                onCancel = {
                    showAddForm = false
                },

                onAddGame = { title, genre, description, isCompleted ->

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

                    showAddForm = false
                }
            )

        } else {

            GameList(
                games = games,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}