package com.pien.moviekmm.android.features.moviedetails.navigation

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailRoute(val movieId: Int, val movieTitle:String, val posterUrl: String)