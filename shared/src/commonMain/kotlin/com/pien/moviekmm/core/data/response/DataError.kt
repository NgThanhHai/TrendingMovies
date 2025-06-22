package com.pien.moviekmm.core.data.response


interface Error
sealed interface DataError : Error {
    enum class Network : DataError {
        NO_INTERNET,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNKNOWN_ERROR
    }
    enum class Local: DataError {
        NOT_IMPLEMENT
    }
}