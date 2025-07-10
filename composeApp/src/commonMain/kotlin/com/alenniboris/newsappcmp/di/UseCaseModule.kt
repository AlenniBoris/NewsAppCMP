package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.IDatabaseRepository
import com.alenniboris.newsappcmp.domain.repository.INewsRepository
import com.alenniboris.newsappcmp.domain.usecase.impl.GetLikedNewsUseCaseImpl
import com.alenniboris.newsappcmp.domain.usecase.impl.GetNewsByQueryUseCaseImpl
import com.alenniboris.newsappcmp.domain.usecase.impl.UpdateArticleIsLikedUseCaseImpl
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetLikedNewsUseCase
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetNewsByQueryUseCase
import com.alenniboris.newsappcmp.domain.usecase.logic.IUpdateArticleIsLikedUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<IGetNewsByQueryUseCase> {
        GetNewsByQueryUseCaseImpl(
            newsRepository = get<INewsRepository>(),
            dispatchers = get<IAppDispatchers>()
        )
    }

    single<IGetLikedNewsUseCase> {
        GetLikedNewsUseCaseImpl(
            databaseRepository = get<IDatabaseRepository>(),
            dispatchers = get<IAppDispatchers>()
        )
    }

    factory<IUpdateArticleIsLikedUseCase> {
        UpdateArticleIsLikedUseCaseImpl(
            databaseRepository = get<IDatabaseRepository>(),
            dispatchers = get<IAppDispatchers>()
        )
    }
}