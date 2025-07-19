package com.alenniboris.newsappcmp.domain.repository

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain

interface IDatabaseRepository {

    suspend fun getLikedNews(): CustomResultModelDomain<List<ArticleModelDomain>, CommonExceptionModelDomain>

    suspend fun addArticleToDatabase(
        article: ArticleModelDomain
    ): CustomResultModelDomain<Unit, CommonExceptionModelDomain>

    suspend fun removeArticleFromDatabase(
        article: ArticleModelDomain
    ): CustomResultModelDomain<Unit, CommonExceptionModelDomain>
}