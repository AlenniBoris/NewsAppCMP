package com.alenniboris.newsappcmp.domain.usecase.impl

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.IDatabaseRepository
import com.alenniboris.newsappcmp.domain.usecase.logic.IUpdateArticleIsLikedUseCase
import kotlinx.coroutines.withContext

class UpdateArticleIsLikedUseCaseImpl(
    private val databaseRepository: IDatabaseRepository,
    private val dispatchers: IAppDispatchers
) : IUpdateArticleIsLikedUseCase {

    override suspend fun invoke(
        article: ArticleModelDomain,
        isLiked: Boolean
    ): CustomResultModelDomain<Unit, CommonExceptionModelDomain> = withContext(dispatchers.IO) {

        if (isLiked) {
            return@withContext databaseRepository.removeArticleFromDatabase(article = article)
        }

        return@withContext databaseRepository.addArticleToDatabase(article = article)
    }
}