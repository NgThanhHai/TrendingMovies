package com.pien.moviekmm.core.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MovieDatabaseConstructor: RoomDatabaseConstructor<MovieDatabase> {
    override fun initialize(): MovieDatabase
}