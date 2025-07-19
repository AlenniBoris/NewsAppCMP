package com.alenniboris.newsappcmp.presentation.screens.saved

import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi

sealed interface ISavedScreenEvent {
    data class NavigateToDetails(val article: ArticleModelUi): ISavedScreenEvent
}