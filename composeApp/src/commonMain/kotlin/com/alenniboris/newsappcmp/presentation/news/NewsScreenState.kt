package com.alenniboris.newsappcmp.presentation.news

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain

data class NewsScreenState(
    val listOfTopics: List<Topic> = allTopics,
    val initialListOfTopics: List<Topic> = allTopics,
    val articles: List<ArticleModelDomain> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
    val isSearchBarActive: Boolean = false,
    val history: Set<String> = emptySet(),
    val selectedTopic: Topic? = initialListOfTopics.firstOrNull()
)

data class Topic(
    val id: Int,
    val text: String
)

val allTopics = listOf(
    Topic(
        id = 0,
        text = "War"
    ),
    Topic(
        id = 1,
        text = "Mobile"
    ),
    Topic(
        id = 2,
        text = "Finance"
    ),
    Topic(
        id = 3,
        text = "World"
    ),
    Topic(
        id = 4,
        text = "Money"
    ),
    Topic(
        id = 5,
        text = "Sport"
    ),
    Topic(
        id = 6,
        text = "Animals"
    ),
    Topic(
        id = 7,
        text = "Videos"
    ),
    Topic(
        id = 8,
        text = "Games"
    )
)