package com.pien.moviekmm.core.data.mapper

import com.pien.moviekmm.core.database.entities.MovieEntity
import com.pien.moviekmm.core.domain.model.Movie

object MovieMapper {
    fun movieToMovieEntity(movie: Movie): MovieEntity {
        return with(movie){
            MovieEntity(
                id = id,
                posterPath = posterPath,
                title = title,
                releaseDate = releaseDate,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }
    fun movieEntityToMovie(movieEntity: MovieEntity): Movie {
        return with(movieEntity){
            Movie(
                id = id,
                posterPath = posterPath,
                title = title,
                releaseDate = releaseDate,
                voteAverage = voteAverage,
                voteCount = voteCount,
            )
        }
    }
}