package com.pien.moviekmm.features.moviedetails

sealed class MovieDetailEvent {
    data class GetMovieDetail(val id: Int): MovieDetailEvent()
}