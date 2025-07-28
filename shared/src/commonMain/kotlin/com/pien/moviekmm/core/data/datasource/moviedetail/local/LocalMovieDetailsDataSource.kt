package com.pien.moviekmm.core.data.datasource.moviedetail.local

import com.pien.moviekmm.core.domain.datasource.MovieDetailDataSource
import com.pien.moviekmm.core.data.mapper.MovieDetailMapper
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.database.daos.MovieDetailDao
import com.pien.moviekmm.core.domain.model.MovieDetail

class LocalMovieDetailsDataSource(private val movieDetailDao: MovieDetailDao, private val movieDetailMapper: MovieDetailMapper): MovieDetailDataSource {
    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError> {
        val result = movieDetailDao.getMovieDetailById(movieId)
        if (result != null && result.id > 0) {
            return Result.Success(movieDetailMapper.movieDetailEntityToMovieDetail(result))
        } else {
            return Result.Success(
                MovieDetail(
                    adult = null,
                    genres = null,
                    homepage = null,
                    id = 0,
                    overview = null,
                    posterPath = null,
                    releaseDate = null,
                    runtime = null,
                    title = "",
                    voteAverage = null,
                    voteCount = null
                )
            )
        }
    }

    suspend fun saveMovieDetail(movieDetail: MovieDetail) {
        movieDetailDao.insertMovieDetail(movieDetail = movieDetailMapper.movieDetailToMovieDetailEntity(movieDetail))
    }
}