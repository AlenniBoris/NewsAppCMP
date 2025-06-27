package com.alenniboris.newsappcmp.presentation.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.alenniboris.newsappcmp.presentation.news.views.NewsScreen
import com.alenniboris.newsappcmp.presentation.saved.views.SavedScreen
import newsappcmp.composeapp.generated.resources.Res
import newsappcmp.composeapp.generated.resources.news_screen
import newsappcmp.composeapp.generated.resources.news_screen_active
import newsappcmp.composeapp.generated.resources.saved_screen
import newsappcmp.composeapp.generated.resources.saved_screen_active
import newsappcmp.composeapp.generated.resources.undefined_screen

val innerNavScreensKeys = listOf(NewsScreen().key, SavedScreen().key)
val innerNavScreens = listOf(NewsScreen(), SavedScreen())

fun Screen.toDrawableRes(isSelected: Boolean = false) = when (this) {
    is NewsScreen ->
        if (isSelected) Res.drawable.news_screen_active
        else Res.drawable.news_screen


    is SavedScreen ->
        if (isSelected) Res.drawable.saved_screen_active
        else Res.drawable.saved_screen


    else -> Res.drawable.undefined_screen
}