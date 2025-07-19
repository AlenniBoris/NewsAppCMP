package com.alenniboris.newsappcmp.presentation.screens.news

import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetNewsByQueryUseCase
import com.alenniboris.newsappcmp.domain.util.SingleFlowEvent
import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi
import com.alenniboris.newsappcmp.presentation.model.toUiModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

@OptIn(FlowPreview::class)
class NewsScreenViewModel(
    private val getNewsByQuery: IGetNewsByQueryUseCase
) : KoinComponent {

    private val _viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    private var _loadingJob: Job? = null

    private val _screenState = MutableStateFlow(NewsScreenState())
    val state = _screenState.asStateFlow()

    private val _event = SingleFlowEvent<INewsScreenEvent>(_viewModelScope)
    val event = _event.flow

    init {
        loadInitialData()
    }

    fun proceedIntent(intent: INewsScreenIntent) = when (intent) {
        is INewsScreenIntent.LoadByQuery -> loadByQuery(intent.query)
        is INewsScreenIntent.RetryLoadByQuery -> retryLoadByQuery()
        is INewsScreenIntent.OnTopicClicked -> onTopicClicked(intent.topic)
        is INewsScreenIntent.UpdateIsSearchBarActive -> updateIsSearchBarActive(intent.isSearchBarActive)
        is INewsScreenIntent.UpdateQuery -> updateQuery(intent.newQuery)
        is INewsScreenIntent.NavigateToDetails -> navigateToDetails(intent.article)
    }

    private fun navigateToDetails(article: ArticleModelUi) {
        _event.emit(
            INewsScreenEvent.NavigateToDetails(article)
        )
    }

    private fun loadByQuery(query: String) {
        _viewModelScope.launch {
            _screenState.update {
                it.copy(
                    query = query.ifEmpty { it.query },
                    selectedTopic = if (query.isEmpty()) it.selectedTopic else null,
                )
            }

            searchInternal(query)
        }
    }

    private fun retryLoadByQuery() {
        _viewModelScope.launch {
            _screenState.update {
                it.copy(
                    selectedTopic = null
                )
            }

            searchInternal(_screenState.value.query)
        }
    }

    private fun searchInternal(query: String) {
        _loadingJob = _viewModelScope.launch {
            _screenState.update {
                it.copy(
                    isSearchBarActive = false,
                    history = if (query.isNotEmpty()) it.history + query else it.history
                )
            }

            if (query.isNotEmpty()) {
                _screenState.update { it.copy(isLoading = true) }

                when (
                    val res = getNewsByQuery.invoke(query)
                ) {
                    is CustomResultModelDomain.Success -> {
                        _screenState.update { it.copy(articles = res.result.map { it.toUiModel() }) }
                    }

                    is CustomResultModelDomain.Error -> {
                        Napier.e(
                            tag = "!!!!",
                            message = res.exception.toString()
                        )
                    }
                }

                _screenState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun loadInitialData() {
        _loadingJob = _viewModelScope.launch {
            _screenState.update { it.copy(isLoading = true) }

            when (
                val res =
                    getNewsByQuery.invoke(query = _screenState.value.listOfTopics.first().text)
            ) {
                is CustomResultModelDomain.Success -> {
                    _screenState.update {
                        it.copy(
                            articles = res.result.map { it.toUiModel() }
                        )
                    }
                }

                is CustomResultModelDomain.Error -> {
                    Napier.e(
                        tag = "!!!!",
                        message = res.exception.toString()
                    )
                }
            }

            _screenState.update { it.copy(isLoading = false) }
        }
    }

    private fun onTopicClicked(topic: Topic) {
        _screenState.update {
            it.copy(
                query = topic.text,
                selectedTopic = topic,
                listOfTopics = it.listOfTopics
            )
        }
        searchInternal(query = topic.text)
    }

    private fun updateIsSearchBarActive(isActive: Boolean) {
        _screenState.update { it.copy(isSearchBarActive = isActive) }
    }

    private fun updateQuery(newQuery: String) {
        _screenState.update {
            it.copy(
                query = newQuery,
                selectedTopic = null
            )
        }
    }

    fun dispose() {
        _loadingJob?.cancel()
    }
}