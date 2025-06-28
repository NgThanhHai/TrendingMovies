package com.pien.moviekmm.core.data.network

import com.pien.moviekmm.SharedBuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

actual class HttpClientFactory {
    actual fun create(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            install(HttpRequestRetry) {
                retryOnServerErrors(5)
                exponentialDelay()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
            install(DefaultRequest) {
                header(HttpHeaders.Authorization, "Bearer ${SharedBuildConfig.API_KEY}")
            }
        }
    }
}