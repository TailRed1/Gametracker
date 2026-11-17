package com.example.tymkov.gametracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tymkov.gametracker.R
import com.example.tymkov.gametracker.model.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    games: List<Game>,
    isLoading: Boolean,
    errorMessage: String?,
    onGameClick: (Int) -> Unit,
    onAddGame: () -> Unit,
    onRefresh: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            R.string.games_title
                        )
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

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Button(
                onClick = onRefresh,
                enabled = !isLoading
            ) {
                Text(
                    text = "Оновити з мережі"
                )
            }

            if (isLoading) {
                CircularProgressIndicator()
            }

            if (errorMessage != null) {
                Text(
                    text = errorMessage
                )
            }

            GameList(
                games = games,
                onGameClick = onGameClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}