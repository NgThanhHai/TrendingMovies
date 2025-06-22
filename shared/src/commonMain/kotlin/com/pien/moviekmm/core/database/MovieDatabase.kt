package com.pien.moviekmm.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pien.moviekmm.core.database.daos.MovieDao
import com.pien.moviekmm.core.database.daos.MovieDetailDao
import com.pien.moviekmm.core.database.entities.MovieDetailEntity
import com.pien.moviekmm.core.database.entities.MovieEntity

@Database(entities = [MovieEntity::class, MovieDetailEntity::class], version = 1)
@TypeConverters(com.pien.moviekmm.core.database.converters.TypeConverters::class)
@ConstructedBy(MovieDatabaseConstructor::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieDetailDao(): MovieDetailDao
}