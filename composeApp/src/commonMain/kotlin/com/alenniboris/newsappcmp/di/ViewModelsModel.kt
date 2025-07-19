package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.domain.usecase.logic.IGetLikedNewsUseCase
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetNewsByQueryUseCase
import com.alenniboris.newsappcmp.domain.usecase.logic.IUpdateArticleIsLikedUseCase
import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi
import com.alenniboris.newsappcmp.presentation.screens.details.DetailsScreenViewModel
import com.alenniboris.newsappcmp.presentation.screens.news.NewsScreenViewModel
import org.koin.dsl.module

val viewModelsModule = module {

    single<NewsScreenViewModel> {
        NewsScreenViewModel(
            getNewsByQuery = get<IGetNewsByQueryUseCase>()
        )
    }

    factory<DetailsScreenViewModel> { (article: ArticleModelUi) ->
        DetailsScreenViewModel(
            article = article,
            updateArticleIsLikedUseCase = get<IUpdateArticleIsLikedUseCase>(),
            getLikedNewsUseCase = get<IGetLikedNewsUseCase>()
        )
    }
}