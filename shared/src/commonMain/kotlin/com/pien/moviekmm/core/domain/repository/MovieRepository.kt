package com.pien.moviekmm.core.domain.repository

import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.domain.model.MovieDetail

interface MovieRepository {
    suspend fun getTrendingMovies(): Result<MoviePaging, DataError>
    suspend fun getLocalTrendingMovies(): Result<MoviePaging, DataError>
    suspend fun searchMovie(query: String): Result<MoviePaging, DataError>
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError>
    suspend fun getLocalMovieDetail(movieId: Int): Result<MovieDetail, DataError>
}