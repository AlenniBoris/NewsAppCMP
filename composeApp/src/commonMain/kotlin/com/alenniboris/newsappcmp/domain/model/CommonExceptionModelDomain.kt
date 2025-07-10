package com.alenniboris.newsappcmp.domain.model

sealed class CommonExceptionModelDomain {

    data object InternetException : CommonExceptionModelDomain()

    data object UnknownException : CommonExceptionModelDomain()

    data object ServerError : CommonExceptionModelDomain()

    data object DatabaseError : CommonExceptionModelDomain()
}