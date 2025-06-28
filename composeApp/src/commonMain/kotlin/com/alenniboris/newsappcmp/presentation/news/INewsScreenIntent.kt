package com.alenniboris.newsappcmp.presentation.news

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain

sealed interface INewsScreenIntent {
    data class LoadByQuery(val query: String) : INewsScreenIntent
    data object RetryLoadByQuery : INewsScreenIntent
    data class UpdateQuery(val newQuery: String) : INewsScreenIntent
    data class UpdateIsSearchBarActive(val isSearchBarActive: Boolean) : INewsScreenIntent
    data class OnTopicClicked(val topic: Topic) : INewsScreenIntent
    data class NavigateToDetails(val article: ArticleModelDomain) : INewsScreenIntent
}