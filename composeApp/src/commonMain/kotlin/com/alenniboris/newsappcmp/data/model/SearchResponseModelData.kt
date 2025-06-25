package com.alenniboris.newsappcmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseModelData(
    val status: String?,
    val totalResults: Int?,
    val articles: List<ArticleModelData?>?
)
