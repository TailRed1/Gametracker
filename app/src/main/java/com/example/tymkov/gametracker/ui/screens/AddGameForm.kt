package com.example.tymkov.gametracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tymkov.gametracker.R

@Composable
fun AddGameForm(
    onAddGame: (
        title: String,
        genre: String,
        description: String?,
        isCompleted: Boolean
    ) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember {
        mutableStateOf("")
    }

    var genre by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var isCompleted by remember {
        mutableStateOf(false)
    }

    val isFormValid =
        title.isNotBlank() &&
                genre.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text(
                    text = stringResource(R.string.game_title)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = genre,
            onValueChange = {
                genre = it
            },
            label = {
                Text(
                    text = stringResource(R.string.game_genre)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text(
                    text = stringResource(R.string.game_description)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 4
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(R.string.game_completed)
            )

            Checkbox(
                checked = isCompleted,
                onCheckedChange = {
                    isCompleted = it
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedButton(
                onClick = onCancel
            ) {
                Text(
                    text = stringResource(R.string.cancel)
                )
            }

            Button(
                onClick = {
                    onAddGame(
                        title.trim(),
                        genre.trim(),
                        description
                            .trim()
                            .takeIf { it.isNotEmpty() },
                        isCompleted
                    )
                },
                enabled = isFormValid
            ) {
                Text(
                    text = stringResource(R.string.save)
                )
            }
        }
    }
}