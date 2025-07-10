package com.alenniboris.newsappcmp.domain.usecase.logic

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import kotlinx.coroutines.flow.SharedFlow

interface IGetLikedNewsUseCase {

    val likedFlow: SharedFlow<List<ArticleModelDomain>>
}