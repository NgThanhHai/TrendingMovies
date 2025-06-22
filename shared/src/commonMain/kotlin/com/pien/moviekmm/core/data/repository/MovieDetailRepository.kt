package com.pien.moviekmm.core.data.repository

import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError>
}