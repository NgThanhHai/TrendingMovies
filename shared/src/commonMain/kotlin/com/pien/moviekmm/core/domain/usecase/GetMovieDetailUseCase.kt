package com.pien.moviekmm.core.domain.usecase

import com.pien.moviekmm.core.data.repository.MovieDetailRepository
import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

class GetMovieDetailUseCase(private val repository: MovieDetailRepository) {
    suspend fun execute(movieId: Int): Result<MovieDetail, DataError> {
        return repository.getMovieDetail(movieId)
    }
}