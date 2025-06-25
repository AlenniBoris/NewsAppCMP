package com.alenniboris.newsappcmp.domain.model

import kotlinx.coroutines.CoroutineDispatcher

interface IAppDispatchers {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Default: CoroutineDispatcher
}