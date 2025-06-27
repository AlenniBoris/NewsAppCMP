package com.alenniboris.newsappcmp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.alenniboris.newsappcmp.presentation.news.views.NewsScreen
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.painterResource

class HomeScreenContainer : Screen {

    @Composable
    override fun Content() {

        Navigator(NewsScreen()) { innerNavigator ->

            val current = innerNavigator.lastItem
            LaunchedEffect(Unit) {
                Napier.d(tag = "!!!!", message = current.key)
            }
            val isBottomBarVisible = innerNavScreensKeys.contains(current.key)
            LaunchedEffect(Unit) {
                Napier.d(tag = "!!!!", message = isBottomBarVisible.toString())
            }
            Scaffold(
                bottomBar = {
                    if (isBottomBarVisible) {
                        NavigationBar {
                            innerNavScreens.forEach { screen ->
                                NavigationBarItem(
                                    selected = screen.key == current.key,
                                    onClick = {
                                        if (current.key != screen.key) {
                                            innerNavigator.push(screen)
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(
                                                screen.toDrawableRes(
                                                    isSelected = screen == current
                                                )
                                            ),
                                            contentDescription = "",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            ) { pv ->
                Box(modifier = Modifier.padding(pv)) {
                    CurrentScreen()
                }
            }
        }
    }
}