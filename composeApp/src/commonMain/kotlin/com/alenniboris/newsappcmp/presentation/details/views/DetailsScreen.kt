package com.alenniboris.newsappcmp.presentation.details.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

data class DetailsScreen(
    val articleJson: String
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Details, article = $articleJson"
            )
            Button(
                modifier = Modifier.padding(top = 15.dp),
                onClick = {
                    navigator?.pop()
                },
                content = {
                    Text(
                        text = "back from details"
                    )
                }
            )
        }
    }
}
