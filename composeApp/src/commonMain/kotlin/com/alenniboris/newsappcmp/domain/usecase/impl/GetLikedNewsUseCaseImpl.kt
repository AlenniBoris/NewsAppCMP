package com.alenniboris.newsappcmp.domain.usecase.impl

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.IDatabaseRepository
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetLikedNewsUseCase
import kotlinx.coroutines.withContext

class GetLikedNewsUseCaseImpl(
    private val databaseRepository: IDatabaseRepository,
    private val dispatchers: IAppDispatchers
) : IGetLikedNewsUseCase {

    override suspend fun invoke(): CustomResultModelDomain<List<ArticleModelDomain>, CommonExceptionModelDomain> =
        withContext(dispatchers.IO) {
            return@withContext databaseRepository.getLikedNews()
        }

}