package com.example.tymkov.gametracker.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.tymkov.gametracker.R
import com.example.tymkov.gametracker.model.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    games: List<Game>,
    onGameClick: (Int) -> Unit,
    onAddGame: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.games_title)
                    )
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddGame
            ) {
                Text("+")
            }
        }

    ) { innerPadding ->

        GameList(
            games = games,
            onGameClick = onGameClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}