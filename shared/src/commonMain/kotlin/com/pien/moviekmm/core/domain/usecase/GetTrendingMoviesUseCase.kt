package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.data.repository.TrendingMovieRepository
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

class GetTrendingMoviesUseCase(private val repository: TrendingMovieRepository) {
    suspend fun execute(isNetworkAvailable: Boolean): Result<MoviePaging, DataError> {
        val result = repository.getLocalTrendingMovies()
        if (result is Result.Success && result.data.results.isNotEmpty()) {
            return result
        }
        return if (isNetworkAvailable) {
            repository.getTrendingMovies()
        } else {
            Result.Failure(DataError.Network.NO_INTERNET)
        }
    }
}