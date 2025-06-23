package com.pien.moviekmm.features.trendingmovies

import com.pien.moviekmm.core.domain.model.Movie

data class TrendingMovieUIState(
    val listMovies: List<Movie> = listOf(),
    val showLoading: Boolean = false,
    val errorToast: String = "",
    val searchText: String = "",
    val showReload: Boolean = false,
    val backgroundUrl: String = "")