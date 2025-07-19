package com.alenniboris.newsappcmp.data.source.local

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDesktopDatabaseBuilder(): RoomDatabase.Builder<ArticleDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "articles_database.db")
    return Room.databaseBuilder(
        name = dbFile.absolutePath
    )
}