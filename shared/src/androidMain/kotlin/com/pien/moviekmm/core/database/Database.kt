package com.pien.moviekmm.core.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

object Database {
    fun getDatabase(ctx: Context): MovieDatabase {
        val appContext = ctx.applicationContext
        val dbFile = appContext.getDatabasePath("movie.db")
        return Room.databaseBuilder<MovieDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver()).build()
    }
}