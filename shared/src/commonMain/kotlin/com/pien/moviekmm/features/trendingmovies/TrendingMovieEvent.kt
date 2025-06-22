package com.pien.moviekmm.features.trendingmovies

sealed class TrendingMovieEvent {
    data object UpdateTrendingMovies: TrendingMovieEvent()
    data class SearchMovies(val query: String): TrendingMovieEvent()
    data class OpenMovieDetail(val movieId: Int, val poster: String): TrendingMovieEvent()
}