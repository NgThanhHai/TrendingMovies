package com.pien.moviekmm.core.data.repository

import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

interface TrendingMovieRepository {
    suspend fun getTrendingMovies(): Result<MoviePaging, DataError>
    suspend fun searchMovie(query: String): Result<MoviePaging, DataError>
}