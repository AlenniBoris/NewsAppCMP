package com.alenniboris.newsappcmp.presentation.screens.saved

import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi

sealed interface ISavedScreenIntent {
    data object RetryLoad : ISavedScreenIntent
    data class NavigateToDetails(val article: ArticleModelUi) : ISavedScreenIntent
}