package com.pien.moviekmm.core.data.repository

import com.pien.moviekmm.core.data.datasource.moviedetail.local.LocalMovieDetailsDataSource
import com.pien.moviekmm.core.data.datasource.trendingmovie.local.LocalTrendingMoviesDataSource
import com.pien.moviekmm.core.data.datasource.moviedetail.remote.RemoteMovieDetailDataSource
import com.pien.moviekmm.core.data.datasource.trendingmovie.remote.RemoteTrendingMoviesDataSource
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteTrendingMoviesDataSource,
    private val localMovieDataSource: LocalTrendingMoviesDataSource,
    private val remoteMovieDetailDataSource: RemoteMovieDetailDataSource,
    private val localMovieDetailDataSource: LocalMovieDetailsDataSource
): MovieRepository {
    override suspend fun getTrendingMovies(): Result<MoviePaging, DataError> {
        val networkQueryResult = remoteMovieDataSource.getTrendingMovies()
        if(networkQueryResult is Result.Success) {
            localMovieDataSource.saveTrendingMovies(networkQueryResult.data.results)
        }
        return networkQueryResult
    }

    override suspend fun getLocalTrendingMovies(): Result<MoviePaging, DataError> {
        return remoteMovieDataSource.getTrendingMovies()
    }

    override suspend fun searchMovie(query: String): Result<MoviePaging, DataError> {
        return remoteMovieDataSource.searchMovie(query)
    }

    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError> {
        val remoteQueryResult = remoteMovieDetailDataSource.getMovieDetail(movieId)
        if (remoteQueryResult is Result.Success) {
            localMovieDetailDataSource.saveMovieDetail(remoteQueryResult.data)
        }
        return remoteQueryResult
    }

    override suspend fun getLocalMovieDetail(movieId: Int): Result<MovieDetail, DataError> {
        return localMovieDetailDataSource.getMovieDetail(movieId)
    }
}