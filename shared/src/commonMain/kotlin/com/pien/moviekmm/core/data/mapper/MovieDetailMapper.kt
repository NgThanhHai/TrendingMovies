package com.pien.moviekmm.core.data.mapper

import com.pien.moviekmm.core.database.entities.MovieDetailEntity
import com.pien.moviekmm.core.domain.model.Genre
import com.pien.moviekmm.core.domain.model.MovieDetail

object MovieDetailMapper {
    fun movieDetailToMovieDetailEntity(movieDetail: MovieDetail): MovieDetailEntity {
        return with(movieDetail){
            MovieDetailEntity(
                id = id,
                posterPath = posterPath,
                title = title,
                releaseDate = releaseDate,
                voteAverage = voteAverage,
                voteCount = voteCount,
                adult = adult,
                genres = genres?.map { it.toString() },
                homepage = homepage,
                overview = overview,
                runtime = runtime,
            )
        }
    }
    fun movieDetailEntityToMovieDetail(movieDetailEntity: MovieDetailEntity): MovieDetail {
        return with(movieDetailEntity){
            MovieDetail(
                id = id,
                posterPath = posterPath,
                title = title,
                releaseDate = releaseDate,
                voteAverage = voteAverage,
                voteCount = voteCount,
                adult = adult,
                genres = genres?.map { Genre.fromString(it) },
                homepage = homepage,
                overview = overview,
                runtime = runtime,
            )
        }
    }
}