package com.alenniboris.newsappcmp.presentation.screens.details

sealed interface IDetailsScreenIntent {

    data object NavigateBack: IDetailsScreenIntent
}