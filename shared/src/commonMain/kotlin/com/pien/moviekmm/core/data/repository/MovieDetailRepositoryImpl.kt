package com.pien.moviekmm.core.data.repository

import com.pien.moviekmm.core.data.local.LocalMovieDetailsDataSource
import com.pien.moviekmm.core.data.network.RemoteMovieDetailDataSource
import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

class MovieDetailRepositoryImpl(
    private val remoteDataSource: RemoteMovieDetailDataSource,
    private val localDataSource: LocalMovieDetailsDataSource
    ): MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError> {
        val remoteQueryResult = remoteDataSource.getMovieDetail(movieId)
        if (remoteQueryResult is Result.Success) {
            localDataSource.saveMovieDetail(remoteQueryResult.data)
        }
        return remoteQueryResult
    }

    override suspend fun getLocalMovieDetail(movieId: Int): Result<MovieDetail, DataError> {
        return localDataSource.getMovieDetail(movieId)
    }
}