package com.alenniboris.newsappcmp.presentation.screens.details

import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetLikedNewsUseCase
import com.alenniboris.newsappcmp.domain.usecase.logic.IUpdateArticleIsLikedUseCase
import com.alenniboris.newsappcmp.domain.util.SingleFlowEvent
import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DetailsScreenViewModel(
    private val article: ArticleModelUi,
    private val getLikedNewsUseCase: IGetLikedNewsUseCase,
    private val updateArticleIsLikedUseCase: IUpdateArticleIsLikedUseCase
) : KoinComponent {

    private val _viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    private val _screenState = MutableStateFlow(DetailsScreenState(article = article))
    val state = _screenState.asStateFlow()

    private val _event = SingleFlowEvent<IDetailsScreenEvent>(_viewModelScope)
    val event = _event.flow

    init {
        loadLiked()
    }

    private fun loadLiked() {
        _viewModelScope.launch {
            Napier.e(tag = "!!!!", message = "loading liked")
            when (
                val res = getLikedNewsUseCase.invoke()
            ) {
                is CustomResultModelDomain.Success -> {
                    val contains = res.result.contains(_screenState.value.article?.domainModel)
                    Napier.e(tag = "!!!!", message = contains.toString())
                    _screenState.update {
                        it.copy(
                            isLiked = contains
                        )
                    }
                }

                is CustomResultModelDomain.Error -> {
                    Napier.d(tag = "!!!!", message = res.exception.toString())
                }
            }
        }
    }

    fun proceedIntent(intent: IDetailsScreenIntent) {
        when (intent) {
            is IDetailsScreenIntent.NavigateBack -> navigateBack()
            is IDetailsScreenIntent.ProceedLikedAction -> proceedLikedAction()
        }
    }

    private fun proceedLikedAction() {
        _screenState.value.article?.domainModel?.let {
            _viewModelScope.launch {
                when (
                    val res = updateArticleIsLikedUseCase.invoke(
                        article = _screenState.value.article!!.domainModel,
                        isLiked = _screenState.value.isLiked
                    )
                ) {
                    is CustomResultModelDomain.Success -> {
                        loadLiked()
                    }

                    is CustomResultModelDomain.Error -> {
                        Napier.d(tag = "!!!!", message = res.exception.toString())
                    }
                }
            }
        }
    }

    private fun navigateBack() {
        _event.emit(
            IDetailsScreenEvent.NavigateBack
        )
    }
}