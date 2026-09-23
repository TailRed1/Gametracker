package com.example.tymkov.gametracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tymkov.gametracker.R
import com.example.tymkov.gametracker.model.Game
import com.example.tymkov.gametracker.ui.components.GameCard

@Composable
fun GameList(
    games: List<Game>,
    modifier: Modifier = Modifier
) {

    if (games.isEmpty()) {

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.empty_games)
            )
        }

    } else {

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = games,
                key = { game -> game.id }
            ) { game ->

                GameCard(
                    game = game
                )
            }
        }
    }
}