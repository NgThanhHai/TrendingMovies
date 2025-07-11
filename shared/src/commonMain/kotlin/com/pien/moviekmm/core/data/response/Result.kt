package com.pien.moviekmm.core.data.response

sealed interface Result<out D, out E : Error> {
    data class Success<out D, out E : Error>(val data: D) : Result<D, E>
    data class Failure<out D, out E : Error>(val error: E) : Result<D, E>
}