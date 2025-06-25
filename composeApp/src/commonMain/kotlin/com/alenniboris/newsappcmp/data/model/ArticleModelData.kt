package com.alenniboris.newsappcmp.data.model

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import kotlinx.serialization.Serializable

@Serializable
data class ArticleModelData(
    val source: ArticleSourceModelData?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

fun ArticleModelData.toModelDomain(): ArticleModelDomain? = runCatching {
    ArticleModelDomain(
        source = this.source?.toModelDomain()!!,
        author = this.author!!,
        title = this.title!!,
        description = this.description!!,
        url = this.url!!,
        urlToImage = this.urlToImage!!,
        publishedAt = this.publishedAt!!,
        content = this.content!!
    )
}.getOrNull()