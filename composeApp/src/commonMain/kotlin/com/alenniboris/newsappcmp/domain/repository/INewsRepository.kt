package com.alenniboris.newsappcmp.domain.repository

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain

interface INewsRepository {

    suspend fun getNewsByQuery(
        query: String
    ): CustomResultModelDomain<List<ArticleModelDomain>, Throwable>
}