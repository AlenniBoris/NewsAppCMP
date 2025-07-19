package com.alenniboris.newsappcmp.presentation.screens.saved

import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi

data class SavedScreenState(
    val isLoading: Boolean = false,
    val articles: List<ArticleModelUi> = emptyList()
)
