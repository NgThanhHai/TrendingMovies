package com.pien.moviekmm.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pien.moviekmm.core.database.entities.MovieEntity

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(item: MovieEntity)

    @Query("SELECT * FROM MovieEntity")
    suspend fun getAllTrendingMovie(): List<MovieEntity>
}