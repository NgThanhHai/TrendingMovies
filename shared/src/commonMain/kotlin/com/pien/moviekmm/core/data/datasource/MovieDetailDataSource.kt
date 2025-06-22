package com.pien.moviekmm.core.data.datasource

import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result

interface MovieDetailDataSource {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError>
}