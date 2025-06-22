package com.pien.moviekmm.core.data.network

import io.ktor.client.*

expect class HttpClientFactory {
    fun create(): HttpClient
}