package com.alenniboris.newsappcmp.data.source.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.ArticleSourceModelDomain

@Entity(tableName = "saved_articles")
data class ArticleEntityModelData(
    @PrimaryKey val id: String,
    @Embedded val source: ArticleSourceEntityModelData,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class ArticleSourceEntityModelData(
    val sourceId: String,
    val name: String
)

fun ArticleSourceModelDomain.toEntityModel(): ArticleSourceEntityModelData =
    ArticleSourceEntityModelData(
        sourceId = this.id,
        name = this.name
    )

fun ArticleModelDomain.toEntityModel(): ArticleEntityModelData =
    ArticleEntityModelData(
        id = this.author + this.title + this.publishedAt + this.url,
        source = this.source.toEntityModel(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )

fun ArticleEntityModelData.toModelDomain() =
    ArticleModelDomain(
        source = ArticleSourceModelDomain(
            id = this.source.sourceId,
            name = this.source.name
        ),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )