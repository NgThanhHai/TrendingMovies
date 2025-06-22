package com.pien.moviekmm.core.data.local

import com.pien.moviekmm.core.data.datasource.TrendMovieDataSource
import com.pien.moviekmm.core.data.mapper.MovieMapper
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.database.daos.MovieDao
import com.pien.moviekmm.core.domain.model.Movie
import com.pien.moviekmm.core.domain.model.MoviePaging

class LocalTrendingMoviesDataSource(private val movieDao: MovieDao, private val mapper: MovieMapper): TrendMovieDataSource {
    override suspend fun getTrendingMovies(): Result<MoviePaging, DataError> {
        val listMovieLocal = mutableListOf<Movie>()
        val result = movieDao.getAllTrendingMovie()
        listMovieLocal.addAll(result.map { movieEntity ->
            mapper.movieEntityToMovie(movieEntity)
        })
        return Result.Success(MoviePaging(results = listMovieLocal))
    }

    override suspend fun searchMovie(query: String): Result<MoviePaging, DataError> {
        return Result.Failure(DataError.Local.NOT_IMPLEMENT)
    }

    suspend fun saveTrendingMovies(list: List<Movie>) {
        list.forEach {
            movieDao.insert(mapper.movieToMovieEntity(it))
        }
    }
}