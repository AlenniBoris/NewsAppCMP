package com.alenniboris.newsappcmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.alenniboris.newsappcmp.presentation.screens.navigation.HomeScreenContainer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            Navigator(HomeScreenContainer()) {
                SlideTransition(it)
            }
        }
    }
}

