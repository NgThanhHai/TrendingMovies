package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.data.repository.TrendingMovieRepository
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

class GetTrendingMoviesUseCase(private val repository: TrendingMovieRepository) {
    suspend fun execute(): Result<MoviePaging, DataError> {
        val result = repository.getTrendingMovies()
        //save
        return result
    }
}