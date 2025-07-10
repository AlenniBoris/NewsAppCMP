package com.alenniboris.newsappcmp.domain.repository

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import kotlinx.coroutines.flow.Flow

interface IDatabaseRepository {

    fun getLikedNews(): Flow<List<ArticleModelDomain>>

    suspend fun addArticleToDatabase(
        article: ArticleModelDomain
    ): CustomResultModelDomain<Unit, CommonExceptionModelDomain>

    suspend fun removeArticleFromDatabase(
        article: ArticleModelDomain
    ): CustomResultModelDomain<Unit, CommonExceptionModelDomain>
}