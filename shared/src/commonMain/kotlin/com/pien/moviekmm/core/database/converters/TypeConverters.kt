package com.pien.moviekmm.core.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class TypeConverters {
    @TypeConverter
    fun  jsonToList(value: String) = Json.decodeFromString<List<String>>(value)

    @TypeConverter
    fun listToJson(list: List<String>) = Json.encodeToString(list)
}