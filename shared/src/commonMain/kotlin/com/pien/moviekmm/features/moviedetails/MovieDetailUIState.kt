package com.pien.moviekmm.features.moviedetails

import com.pien.moviekmm.core.domain.model.MovieDetail

data class MovieDetailUIState(
    val movieDetail: MovieDetail? = null,
    val showLoading: Boolean = false,
    val error: String = "")
