package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.domain.usecase.logic.IGetNewsByQueryUseCase
import com.alenniboris.newsappcmp.presentation.news.NewsScreenViewModel
import org.koin.dsl.module

val viewModelsModule = module {

    single<NewsScreenViewModel> {
        NewsScreenViewModel(
            getNewsByQuery = get<IGetNewsByQueryUseCase>()
        )
    }
}