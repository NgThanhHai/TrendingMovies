package com.pien.moviekmm.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val voteCount: Int?
)