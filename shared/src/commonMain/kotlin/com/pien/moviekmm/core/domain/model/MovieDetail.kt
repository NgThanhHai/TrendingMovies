package com.pien.moviekmm.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    val adult: Boolean?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int,
    val overview: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    val runtime: Long?,
    val title: String,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Long?) {
    companion object {
        fun List<Genre>.getListGenresContent(): String {
            var content = ""
            this.forEach {
                content += if(it.toString() == this.last().toString()) {
                    it.name
                } else {
                    "${it.name}, "
                }
            }
            return content
        }
    }
}