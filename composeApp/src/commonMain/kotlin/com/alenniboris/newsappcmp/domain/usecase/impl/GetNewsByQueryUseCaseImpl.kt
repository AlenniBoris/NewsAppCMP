package com.alenniboris.newsappcmp.domain.usecase.impl

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.INewsRepository
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetNewsByQueryUseCase
import kotlinx.coroutines.withContext

class GetNewsByQueryUseCaseImpl(
    private val newsRepository: INewsRepository,
    private val dispatchers: IAppDispatchers
) : IGetNewsByQueryUseCase {

    override suspend fun invoke(
        query: String
    ): CustomResultModelDomain<List<ArticleModelDomain>, CommonExceptionModelDomain> =
        withContext(dispatchers.IO) {
            return@withContext newsRepository.getNewsByQuery(
                query = query
            )
        }
}