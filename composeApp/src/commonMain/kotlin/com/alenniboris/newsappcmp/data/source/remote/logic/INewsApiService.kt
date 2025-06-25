package com.alenniboris.newsappcmp.data.source.remote.logic

import com.alenniboris.newsappcmp.data.model.SearchResponseModelData
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain

interface INewsApiService {

    suspend fun getNewsByQuery(
        query: String
    ): CustomResultModelDomain<SearchResponseModelData, CommonExceptionModelDomain>
}