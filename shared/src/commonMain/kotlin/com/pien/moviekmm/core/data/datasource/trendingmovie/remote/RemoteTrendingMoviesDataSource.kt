package com.pien.moviekmm.core.data.datasource.trendingmovie.remote

import com.pien.moviekmm.SharedBuildConfig
import com.pien.moviekmm.core.domain.datasource.TrendMovieDataSource
import com.pien.moviekmm.core.domain.model.MoviePaging
import com.pien.moviekmm.core.data.response.DataError
import com.pien.moviekmm.core.data.response.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.io.IOException

class RemoteTrendingMoviesDataSource(
    private val httpClient: HttpClient
): TrendMovieDataSource {
    override suspend fun getTrendingMovies(): Result<MoviePaging, DataError> {
        try {
            val response = httpClient.get {
                url(SharedBuildConfig.BASE_URL + "/trending/movie/day?language=en-US")
                accept(ContentType.Application.Json)
            }

            return when (response.status.value) {
                in 200..299 -> {
                    val result = response.body<MoviePaging>()
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

    override suspend fun searchMovie(query: String): Result<MoviePaging, DataError> {
        try {
            val response = httpClient.get {
                url(SharedBuildConfig.BASE_URL + "/search/movie")
                accept(ContentType.Application.Json)
                parameter("query", query)
                parameter("language", "en-US")
            }

            return when (response.status.value) {
                in 200..299 -> {
                    val result = response.body<MoviePaging>()
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