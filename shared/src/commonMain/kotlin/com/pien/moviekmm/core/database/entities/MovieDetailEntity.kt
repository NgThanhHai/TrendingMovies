package com.pien.moviekmm.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Int = 0,
    val id: Int = 0,
    val adult: Boolean?,
    val genres: List<String>?,
    val homepage: String?,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val runtime: Long?,
    val title: String,
    val voteAverage: Double?,
    val voteCount: Long?
)