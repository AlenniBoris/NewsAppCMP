package com.alenniboris.newsappcmp.domain.usecase.logic

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain

interface IGetLikedNewsUseCase {

    suspend fun invoke(): CustomResultModelDomain<List<ArticleModelDomain>, CommonExceptionModelDomain>
}