package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.data.repository.MovieRepository
import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import com.pien.moviekmm.core.domain.networkconnectivity.NetworkConnectivity

class GetMovieDetailUseCase(private val repository: MovieRepository, private val connectivity: NetworkConnectivity) {

    suspend fun execute(movieId: Int): Result<MovieDetail, DataError> {
        val localQueryResult = repository.getLocalMovieDetail(movieId)
        if (localQueryResult is Result.Success && localQueryResult.data.id != 0) {
            return localQueryResult
        }
        return if(connectivity.isNetworkConnected()) repository.getMovieDetail(movieId) else Result.Failure(DataError.Network.NO_INTERNET)
    }
}