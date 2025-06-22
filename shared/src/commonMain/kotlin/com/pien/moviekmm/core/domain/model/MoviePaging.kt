package com.pien.moviekmm.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviePaging(
    var page: Int? = null,
    var results: List<Movie> = listOf(),
    var totalPages: Int? = null,
    var totalResults: Int? = null)
