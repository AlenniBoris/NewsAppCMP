package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.data.source.local.ArticleDatabase
import com.alenniboris.newsappcmp.data.source.local.getArticleDatabase
import com.alenniboris.newsappcmp.data.source.local.getDesktopDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module = module {
    single<ArticleDatabase> {
        getArticleDatabase(
            builder = getDesktopDatabaseBuilder()
        )
    }
}