package com.example.tymkov.gametracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tymkov.gametracker.R
import androidx.compose.foundation.layout.safeDrawingPadding

@Composable
fun AddGameScreen(
    onAddGame: (
        title: String,
        genre: String,
        description: String?,
        isCompleted: Boolean
    ) -> Unit,

    onCancel: () -> Unit,

    modifier: Modifier = Modifier
) {

    var title by rememberSaveable {
        mutableStateOf("")
    }

    var genre by rememberSaveable {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }

    var isCompleted by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TextButton(
            onClick = {
                title = ""
                genre = ""
                description = ""
                isCompleted = false
            }
        ) {
            Text(
                text = stringResource(R.string.clear_form)
            )
        }

        AddGameForm(
            title = title,
            genre = genre,
            description = description,
            isCompleted = isCompleted,

            onTitleChange = {
                title = it
            },

            onGenreChange = {
                genre = it
            },

            onDescriptionChange = {
                description = it
            },

            onCompletedChange = {
                isCompleted = it
            },

            onSave = {
                onAddGame(
                    title.trim(),
                    genre.trim(),
                    description
                        .trim()
                        .takeIf { it.isNotEmpty() },
                    isCompleted
                )
            },

            onCancel = onCancel
        )
    }
}