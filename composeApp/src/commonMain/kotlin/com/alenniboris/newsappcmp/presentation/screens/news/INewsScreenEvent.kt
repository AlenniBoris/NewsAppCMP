package com.alenniboris.newsappcmp.presentation.screens.news

import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi

sealed interface INewsScreenEvent {

    data class NavigateToDetails(val article: ArticleModelUi) : INewsScreenEvent
}