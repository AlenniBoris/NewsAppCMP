package com.alenniboris.newsappcmp.data.source.local

import androidx.room.ConstructedBy
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

@Database(
    entities = [ArticleEntityModelData::class],
    version = 1,
    exportSchema = true
)
@ConstructedBy(ArticleDatabaseConstructor::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ArticleDatabaseConstructor : RoomDatabaseConstructor<ArticleDatabase> {
    override fun initialize(): ArticleDatabase
}

fun getArticleDatabase(builder: RoomDatabase.Builder<ArticleDatabase>): ArticleDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .build()
}

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticleToDatabase(article: ArticleEntityModelData)

    @Delete
    suspend fun deleteArticleFromDatabase(article: ArticleEntityModelData)

    @Query("SELECT * FROM saved_articles")
    suspend fun getAllArticles(): List<ArticleEntityModelData>
}