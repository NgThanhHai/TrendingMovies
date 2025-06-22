package com.pien.moviekmm.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pien.moviekmm.core.database.entities.MovieDetailEntity

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity)

    @Query("SELECT * FROM MovieDetailEntity WHERE id=:id")
    suspend fun getMovieDetailById(id: Int): MovieDetailEntity?
}