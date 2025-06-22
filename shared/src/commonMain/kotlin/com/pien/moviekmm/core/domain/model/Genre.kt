package com.pien.moviekmm.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Long,
    val name: String) {

    override fun toString(): String {
        return "$id|$name"
    }
    companion object {
        fun fromString(str: String): Genre {
            val parts = str.split('|')
            var id = 0L
            var name = ""
            if (parts.isNotEmpty()) {
                id = parts[0].toLong()
                name = parts[1]
            }
            return Genre(id, name)
        }
    }
}
