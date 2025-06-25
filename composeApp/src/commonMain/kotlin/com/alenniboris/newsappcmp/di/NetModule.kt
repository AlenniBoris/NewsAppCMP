package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.data.source.remote.KtorClientFactory
import com.alenniboris.newsappcmp.data.source.remote.impl.NewsApiServiceImpl
import com.alenniboris.newsappcmp.data.source.remote.logic.INewsApiService
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import io.ktor.client.HttpClient
import org.koin.dsl.module

val netModule = module {

    single<HttpClient> {
        KtorClientFactory().createClient()
    }

    single<INewsApiService> {
        NewsApiServiceImpl(
            client = get<HttpClient>(),
            dispatchers = get<IAppDispatchers>()
        )
    }
}