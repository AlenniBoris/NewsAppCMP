package com.alenniboris.newsappcmp.domain.model

import java.util.Date

data class ArticleModelDomain(
    val source: ArticleSourceModelDomain,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String
)
