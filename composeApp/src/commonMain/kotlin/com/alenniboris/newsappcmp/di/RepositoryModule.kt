package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.data.repository.NewsRepositoryImpl
import com.alenniboris.newsappcmp.data.source.remote.logic.INewsApiService
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.INewsRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<INewsRepository> {
        NewsRepositoryImpl(
            dispatchers = get<IAppDispatchers>(),
            apiService = get<INewsApiService>()
        )
    }
}