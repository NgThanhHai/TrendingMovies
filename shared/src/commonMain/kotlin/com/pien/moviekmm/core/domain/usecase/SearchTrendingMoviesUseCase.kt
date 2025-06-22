package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.data.repository.TrendingMovieRepository
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

class SearchTrendingMoviesUseCase(private val repository: TrendingMovieRepository) {
    suspend fun execute(query: String): Result<MoviePaging, DataError> {
        val result = repository.searchMovie(query)
        //no cache needed
        return result
    }
}