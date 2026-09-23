package com.example.tymkov.gametracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tymkov.gametracker.R
import com.example.tymkov.gametracker.model.Game
import com.example.tymkov.gametracker.ui.theme.GameTrackerTheme

@Composable
fun GameCard(
    game: Game,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = game.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = game.genre,
                style = MaterialTheme.typography.bodyMedium
            )

            game.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = if (game.isCompleted) {
                    stringResource(R.string.status_completed)
                } else {
                    stringResource(R.string.status_not_completed)
                },
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCardPreview() {

    GameTrackerTheme {

        GameCard(
            game = Game(
                id = 1,
                title = "Cyberpunk 2077",
                genre = "RPG",
                description = "Відкрита рольова гра у футуристичному світі Night City.",
                isCompleted = true
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}