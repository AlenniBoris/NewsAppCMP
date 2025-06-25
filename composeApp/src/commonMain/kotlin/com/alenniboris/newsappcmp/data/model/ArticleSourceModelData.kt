package com.alenniboris.newsappcmp.data.model

import com.alenniboris.newsappcmp.domain.model.ArticleSourceModelDomain
import kotlinx.serialization.Serializable

@Serializable
data class ArticleSourceModelData(
    val id: String?,
    val name: String?
)

fun ArticleSourceModelData.toModelDomain(): ArticleSourceModelDomain? = runCatching {
    ArticleSourceModelDomain(
        id = id!!,
        name = name!!
    )
}.getOrNull()