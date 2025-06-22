package com.pien.moviekmm.features.trendingmovies

import com.pien.moviekmm.core.domain.model.Movie

data class TrendingMovieUIState(
    var listMovies: List<Movie> = listOf(),
    var showLoading: Boolean = false,
    var errorToast: String = "",
    var searchText: String = "",
    var showReload: Boolean = false,
    var backgroundUrl: String = "")