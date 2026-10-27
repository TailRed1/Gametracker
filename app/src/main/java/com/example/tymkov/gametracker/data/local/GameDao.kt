package com.example.tymkov.gametracker.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query(
        """
        SELECT * FROM games
        ORDER BY id DESC
        """
    )
    fun observeAll(): Flow<List<GameEntity>>

    @Insert
    suspend fun insert(
        game: GameEntity
    )

    @Update
    suspend fun update(
        game: GameEntity
    )
}