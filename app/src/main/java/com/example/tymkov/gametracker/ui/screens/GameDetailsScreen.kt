package com.example.tymkov.gametracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tymkov.gametracker.R
import com.example.tymkov.gametracker.model.Game
import androidx.compose.foundation.layout.safeDrawingPadding

@Composable
fun GameDetailsScreen(
    game: Game?,
    onToggleCompleted: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (game == null) {

        Box(
            modifier = modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.game_not_found)
            )
        }

        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TextButton(
            onClick = onBack
        ) {
            Text(
                text = stringResource(R.string.back)
            )
        }

        Text(
            text = game.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = game.genre,
            style = MaterialTheme.typography.titleMedium
        )

        game.description?.let { description ->
            Text(
                text = description
            )
        }

        Text(
            text = if (game.isCompleted) {
                stringResource(R.string.status_completed)
            } else {
                stringResource(R.string.status_not_completed)
            }
        )

        Button(
            onClick = onToggleCompleted
        ) {
            Text(
                text = if (game.isCompleted) {
                    stringResource(R.string.mark_not_completed)
                } else {
                    stringResource(R.string.mark_completed)
                }
            )
        }
    }
}
