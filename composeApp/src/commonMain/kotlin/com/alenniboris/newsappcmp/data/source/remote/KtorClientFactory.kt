package com.alenniboris.newsappcmp.data.source.remote

import io.ktor.client.HttpClient

expect class KtorClientFactory() {
    fun createClient(): HttpClient
}