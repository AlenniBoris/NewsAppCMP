package com.alenniboris.newsappcmp.data.source.remote

import com.alenniboris.newsappcmp.BuildConfig

actual fun getNewsApiKey(): String = BuildConfig.NEWS_API_KEY