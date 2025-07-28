package com.pien.moviekmm.core.data.datasource.moviedetail.remote

import com.pien.moviekmm.core.domain.datasource.MovieDetailDataSource
import com.pien.moviekmm.core.domain.model.MovieDetail
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.io.IOException

class RemoteMovieDetailDataSource(private val httpClient: HttpClient): MovieDetailDataSource {
    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError> {
        try {
            val response = httpClient.get {
                url("https://api.themoviedb.org/3/movie/$movieId?language=en-US")
                accept(ContentType.Application.Json)
            }

            return when (response.status.value) {
                in 200..299 -> {
                    val result = response.body<MovieDetail>()
                    Result.Success(result)
                }

                500 -> Result.Failure(DataError.Network.SERVER_ERROR)
                in 400..499 -> Result.Failure(DataError.Network.CLIENT_ERROR)
                else -> Result.Failure(DataError.Network.UNKNOWN_ERROR)
            }

        } catch (ex: IOException) {
            return Result.Failure(DataError.Network.SERVER_ERROR)
        }
    }
}