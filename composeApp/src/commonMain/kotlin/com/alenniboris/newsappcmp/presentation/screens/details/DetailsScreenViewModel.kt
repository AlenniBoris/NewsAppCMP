package com.alenniboris.newsappcmp.presentation.screens.details

import com.alenniboris.newsappcmp.domain.util.SingleFlowEvent
import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class DetailsScreenViewModel(
    private val article: ArticleModelUi
) : KoinComponent {

    private val _viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    private val _screenState = MutableStateFlow(DetailsScreenState(article = article))
    val state = _screenState.asStateFlow()

    private val _event = SingleFlowEvent<IDetailsScreenEvent>(_viewModelScope)
    val event = _event.flow

    fun proceedIntent(intent: IDetailsScreenIntent) {
        when (intent) {
            is IDetailsScreenIntent.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        _event.emit(
            IDetailsScreenEvent.NavigateBack
        )
    }
}