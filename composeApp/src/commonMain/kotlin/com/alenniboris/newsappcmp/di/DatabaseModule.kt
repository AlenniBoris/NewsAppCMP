package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.data.source.local.ArticleDao
import com.alenniboris.newsappcmp.data.source.local.ArticleDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect val databaseModule: Module

val daoModule = module {
    single<ArticleDao> {
        get<ArticleDatabase>().articleDao
    }
}