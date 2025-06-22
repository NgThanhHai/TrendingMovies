package com.pien.moviekmm.core.data.repository
import com.pien.moviekmm.core.data.local.LocalTrendingMoviesDataSource
import com.pien.moviekmm.core.data.network.RemoteTrendingMoviesDataSource
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

class TrendingMovieRepositoryImpl(
    private val remoteDataSource: RemoteTrendingMoviesDataSource,
    private val localDataSource: LocalTrendingMoviesDataSource): TrendingMovieRepository {
    override suspend fun getTrendingMovies(): Result<MoviePaging, DataError> {
        val networkQueryResult = remoteDataSource.getTrendingMovies()
        if(networkQueryResult is Result.Success) {
            localDataSource.saveTrendingMovies(networkQueryResult.data.results)
        }
        return networkQueryResult
    }

    override suspend fun getLocalTrendingMovies(): Result<MoviePaging, DataError> {
        return localDataSource.getTrendingMovies()
    }

    override suspend fun searchMovie(query: String): Result<MoviePaging, DataError> {
        return remoteDataSource.searchMovie(query)
    }
}