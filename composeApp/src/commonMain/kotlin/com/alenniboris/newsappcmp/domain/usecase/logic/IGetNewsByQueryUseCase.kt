package com.alenniboris.newsappcmp.domain.usecase.logic

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain

interface IGetNewsByQueryUseCase {

    suspend fun invoke(
        query: String
    ): CustomResultModelDomain<List<ArticleModelDomain>, CommonExceptionModelDomain>
}