package com.pien.moviekmm.core.data.datasource

import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

interface TrendMovieDataSource {
    suspend fun getTrendingMovies(): Result<MoviePaging, DataError>
    suspend fun searchMovie(query: String): Result<MoviePaging, DataError>
}