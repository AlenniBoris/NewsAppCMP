package com.alenniboris.newsappcmp.data.source.local

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase


fun getAndroidDatabaseBuilder(apl: Application): RoomDatabase.Builder<ArticleDatabase> {
    val dbFile = apl.getDatabasePath("articles_database.db")
    return Room.databaseBuilder(
        context = apl,
        name = dbFile.absolutePath
    )
}