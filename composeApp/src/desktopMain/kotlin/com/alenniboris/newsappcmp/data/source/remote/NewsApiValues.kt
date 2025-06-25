package com.alenniboris.newsappcmp.data.source.remote

actual fun getNewsApiKey(): String = System.getProperty("NEWS_API_KEY") ?: error("NEWS_API_KEY not found")