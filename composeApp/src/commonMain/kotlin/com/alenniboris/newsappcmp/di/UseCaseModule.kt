package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.INewsRepository
import com.alenniboris.newsappcmp.domain.usecase.impl.GetNewsByQueryUseCaseImpl
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetNewsByQueryUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<IGetNewsByQueryUseCase> {
        GetNewsByQueryUseCaseImpl(
            newsRepository = get<INewsRepository>(),
            dispatchers = get<IAppDispatchers>()
        )
    }
}