package com.alenniboris.newsappcmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.repository.INewsRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import newsappcmp.composeapp.generated.resources.Res
import newsappcmp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    repository: INewsRepository
) {
    MaterialTheme {

        LaunchedEffect(Unit) {
            CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
                when (
                    val res = repository.getNewsByQuery(query = "War")
                ) {
                    is CustomResultModelDomain.Success -> {
                        Napier.d(tag = "!!!!", message = res.result.size.toString())
                        res.result.forEach { article ->
                            Napier.d(tag = "!!!!", message = article.toString())
                        }
                    }

                    is CustomResultModelDomain.Error -> {
                        Napier.d(tag = "!!!!", message = res.exception.toString())
                    }
                }
            }
        }

        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}