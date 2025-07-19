package com.alenniboris.newsappcmp.presentation.screens.saved

import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetLikedNewsUseCase
import com.alenniboris.newsappcmp.domain.util.SingleFlowEvent
import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi
import com.alenniboris.newsappcmp.presentation.model.toUiModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SavedScreenViewModel(
    private val getLikedNewsUseCase: IGetLikedNewsUseCase
) : KoinComponent {

    private val _viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableStateFlow(SavedScreenState())
    val state = _state.asStateFlow()

    private val _event = SingleFlowEvent<ISavedScreenEvent>(_viewModelScope)
    val event = _event.flow

    private var _loadingJob: Job? = null

    init {
        loadArticles()
    }

    private fun loadArticles() {

        _loadingJob?.cancel()
        _loadingJob = _viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (
                val res = getLikedNewsUseCase.invoke()
            ) {
                is CustomResultModelDomain.Success -> {
                    _state.update { it.copy(articles = res.result.map { it.toUiModel() }) }
                }

                is CustomResultModelDomain.Error -> {
                    Napier.e(tag = "!!!!", message = res.exception.toString())
                }
            }

            _state.update { it.copy(isLoading = false) }
        }
    }

    fun proceedIntent(intent: ISavedScreenIntent) {
        when (intent) {
            is ISavedScreenIntent.NavigateToDetails ->
                navigateToDetails(intent.article)

            is ISavedScreenIntent.RetryLoad ->
                loadArticles()
        }
    }

    private fun navigateToDetails(article: ArticleModelUi) {
        _event.emit(
            ISavedScreenEvent.NavigateToDetails(article)
        )
    }

    fun dispose() {
        _loadingJob?.cancel()
    }
}