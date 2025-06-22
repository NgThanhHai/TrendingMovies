package com.pien.moviekmm.features.moviedetails

import com.pien.moviekmm.core.domain.model.MovieDetail

data class MovieDetailUIState(var movieDetail: MovieDetail? = null, var showLoading: Boolean = false, var error: String = "")
