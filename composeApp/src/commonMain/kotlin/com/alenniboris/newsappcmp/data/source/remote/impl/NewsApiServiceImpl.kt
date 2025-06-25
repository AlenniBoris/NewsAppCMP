package com.alenniboris.newsappcmp.data.source.remote.impl

import com.alenniboris.newsappcmp.data.model.SearchResponseModelData
import com.alenniboris.newsappcmp.data.source.remote.NewsApiValues
import com.alenniboris.newsappcmp.data.source.remote.getNewsApiKey
import com.alenniboris.newsappcmp.data.source.remote.logic.INewsApiService
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import kotlinx.coroutines.withContext

class NewsApiServiceImpl(
    private val client: HttpClient,
    private val dispatchers: IAppDispatchers
) : INewsApiService {

    override suspend fun getNewsByQuery(
        query: String
    ): CustomResultModelDomain<SearchResponseModelData, CommonExceptionModelDomain> =
        withContext(dispatchers.IO) {

            val httpResponse = client.get(NewsApiValues.SEARCH_BY_QUERY_STRING) {
                parameter(NewsApiValues.QUERY_PARAMETER, query)
                parameter(NewsApiValues.APIKEY_PARAMETER, getNewsApiKey())
            }

            if (!httpResponse.status.isSuccess()) {
                return@withContext CustomResultModelDomain.Error(
                    CommonExceptionModelDomain.UnknownException
                )
            }

            val response = httpResponse.body<SearchResponseModelData>()
            return@withContext CustomResultModelDomain.Success(
                response
            )
        }
}