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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tymkov.gametracker.R

@Composable
fun AddGameForm(
    title: String,
    genre: String,
    description: String,
    isCompleted: Boolean,

    onTitleChange: (String) -> Unit,
    onGenreChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onCompletedChange: (Boolean) -> Unit,

    onSave: () -> Unit,
    onCancel: () -> Unit,

    modifier: Modifier = Modifier
) {

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
            onValueChange = onTitleChange,
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
            onValueChange = onGenreChange,
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
            onValueChange = onDescriptionChange,
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
                onCheckedChange = onCompletedChange
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
                onClick = onSave,
                enabled = isFormValid
            ) {
                Text(
                    text = stringResource(R.string.save)
                )
            }
        }
    }
}