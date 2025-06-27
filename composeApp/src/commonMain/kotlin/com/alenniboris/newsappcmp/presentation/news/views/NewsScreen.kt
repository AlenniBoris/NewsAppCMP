package com.alenniboris.newsappcmp.presentation.news.views

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
import com.alenniboris.newsappcmp.presentation.details.views.DetailsScreen

class NewsScreen : Screen {

    @Composable
    override fun Content() {

        val parentNavigator = LocalNavigator.current
        val navigator = parentNavigator?.parent ?: parentNavigator

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "News"
            )
            Button(
                modifier = Modifier.padding(top = 15.dp),
                onClick = {
                    navigator?.push(
                        DetailsScreen(id = "10")
                    )
                },
                content = {
                    Text(
                        text = "details, id = 10"
                    )
                }
            )
        }
    }
}