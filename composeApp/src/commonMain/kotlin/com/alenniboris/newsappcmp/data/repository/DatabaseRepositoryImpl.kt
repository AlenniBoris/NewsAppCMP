package com.alenniboris.newsappcmp.data.repository

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.IDatabaseRepository
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(
    private val dispatchers: IAppDispatchers
) : IDatabaseRepository {

    override fun getLikedNews(): Flow<List<ArticleModelDomain>> {
        TODO("Not yet implemented")
    }

    override suspend fun addArticleToDatabase(article: ArticleModelDomain): CustomResultModelDomain<Unit, CommonExceptionModelDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun removeArticleFromDatabase(article: ArticleModelDomain): CustomResultModelDomain<Unit, CommonExceptionModelDomain> {
        TODO("Not yet implemented")
    }
}