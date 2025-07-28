package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.domain.repository.MovieRepository
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.domain.networkconnectivity.NetworkConnectivity

class GetTrendingMoviesUseCase(private val repository: MovieRepository, private val connectivity: NetworkConnectivity) {

    suspend fun execute(): Result<MoviePaging, DataError> {
        val result = repository.getLocalTrendingMovies()
        if (result is Result.Success && result.data.results.isNotEmpty()) {
            return result
        }
        return if (connectivity.isNetworkConnected()) {
            repository.getTrendingMovies()
        } else {
            Result.Failure(DataError.Network.NO_INTERNET)
        }
    }
}