package com.alenniboris.newsappcmp.domain.model

data class ArticleModelDomain(
    val source: ArticleSourceModelDomain,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
