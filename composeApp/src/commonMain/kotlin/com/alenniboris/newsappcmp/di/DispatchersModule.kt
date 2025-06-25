package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module {

    single<IAppDispatchers> {
        object : IAppDispatchers {
            override val Main: CoroutineDispatcher = Dispatchers.Main
            override val IO: CoroutineDispatcher = Dispatchers.IO
            override val Default: CoroutineDispatcher = Dispatchers.Default
        }
    }
}