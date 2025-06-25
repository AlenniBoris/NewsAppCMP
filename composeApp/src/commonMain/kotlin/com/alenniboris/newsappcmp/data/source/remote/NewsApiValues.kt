package com.alenniboris.newsappcmp.data.source.remote

object NewsApiValues {

    const val BASE_URL = "https://newsapi.org/v2/"

    const val APIKEY_PARAMETER = "apiKey"

    const val SEARCH_BY_QUERY_STRING = BASE_URL + "everything"

    const val QUERY_PARAMETER = "q"
}

expect fun getNewsApiKey(): String