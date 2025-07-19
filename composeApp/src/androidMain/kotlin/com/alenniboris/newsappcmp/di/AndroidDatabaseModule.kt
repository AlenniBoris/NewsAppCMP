package com.alenniboris.newsappcmp.di

import com.alenniboris.newsappcmp.data.source.local.ArticleDatabase
import com.alenniboris.newsappcmp.data.source.local.getAndroidDatabaseBuilder
import com.alenniboris.newsappcmp.data.source.local.getArticleDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module


actual val databaseModule: Module = module {

    single<ArticleDatabase> {
        getArticleDatabase(
            builder = getAndroidDatabaseBuilder(apl = androidApplication())
        )
    }
}