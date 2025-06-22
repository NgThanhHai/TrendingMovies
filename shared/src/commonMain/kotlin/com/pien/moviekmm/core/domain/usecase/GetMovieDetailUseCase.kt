package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.data.repository.MovieDetailRepository
import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

class GetMovieDetailUseCase(private val repository: MovieDetailRepository) {
    suspend fun execute(movieId: Int, isNetworkAvailable: Boolean): Result<MovieDetail, DataError> {
        val localQueryResult = repository.getLocalMovieDetail(movieId)
        if (localQueryResult is Result.Success && localQueryResult.data.id != 0) {
            return localQueryResult
        }
        return if (isNetworkAvailable) {
            repository.getMovieDetail(movieId)
        } else {
            Result.Failure(DataError.Network.NO_INTERNET)
        }
    }
}