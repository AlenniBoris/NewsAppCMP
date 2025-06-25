package com.alenniboris.newsappcmp.data.repository

import com.alenniboris.newsappcmp.data.model.toModelDomain
import com.alenniboris.newsappcmp.data.source.remote.logic.INewsApiService
import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.INewsRepository
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val apiService: INewsApiService,
    private val dispatchers: IAppDispatchers
) : INewsRepository {

    override suspend fun getNewsByQuery(
        query: String
    ): CustomResultModelDomain<List<ArticleModelDomain>, CommonExceptionModelDomain> =
        withContext(dispatchers.IO) {
            return@withContext when (
                val serviceResponse = apiService.getNewsByQuery(
                    query = query
                )
            ) {
                is CustomResultModelDomain.Success -> {

                    val result = serviceResponse.result.articles?.let { articles ->
                        articles.mapNotNull { it?.toModelDomain() }
                    } ?: return@withContext CustomResultModelDomain.Error(
                        CommonExceptionModelDomain.ServerError
                    )

                    CustomResultModelDomain.Success(result)
                }

                is CustomResultModelDomain.Error -> {
                    CustomResultModelDomain.Error(
                        serviceResponse.exception
                    )
                }
            }
        }
}