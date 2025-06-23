package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.data.repository.MovieRepository
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.domain.networkconnectivity.NetworkConnectivity

class SearchTrendingMoviesUseCase(private val repository: MovieRepository, private val networkConnectivity: NetworkConnectivity) {

    suspend fun execute(query: String): Result<MoviePaging, DataError> {
        //no cache needed
        return if (networkConnectivity.isNetworkConnected()) repository.searchMovie(query) else Result.Failure(DataError.Network.NO_INTERNET)
    }
}