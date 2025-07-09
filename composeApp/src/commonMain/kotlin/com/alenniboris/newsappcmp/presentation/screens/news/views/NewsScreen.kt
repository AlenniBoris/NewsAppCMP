package com.alenniboris.newsappcmp.presentation.screens.news.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.alenniboris.newsappcmp.domain.util.GsonUtil.toJson
import com.alenniboris.newsappcmp.presentation.screens.details.views.DetailsScreen
import com.alenniboris.newsappcmp.presentation.screens.news.INewsScreenEvent
import com.alenniboris.newsappcmp.presentation.screens.news.INewsScreenIntent
import com.alenniboris.newsappcmp.presentation.screens.news.NewsScreenState
import com.alenniboris.newsappcmp.presentation.screens.news.NewsScreenViewModel
import com.alenniboris.newsappcmp.presentation.utils.views.AppSearchBar
import com.alenniboris.newsappcmp.presentation.utils.views.ArticlePlaceholder
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


class NewsScreen : Screen {

    @Composable
    override fun Content() {

        val parentNavigator = LocalNavigator.current
        val navigator = parentNavigator?.parent ?: parentNavigator

        val viewModel = koinInject<NewsScreenViewModel>()

        val state by viewModel.state.collectAsStateWithLifecycle()

        val proceedIntent by remember { mutableStateOf(viewModel::proceedIntent) }

        val event by remember { mutableStateOf(viewModel.event) }

        LaunchedEffect(event) {
            launch {
                event.filterIsInstance<INewsScreenEvent.NavigateToDetails>().collect { coming ->
                    navigator?.push(
                        DetailsScreen(coming.article.toJson())
                    )
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                viewModel.dispose()
            }
        }

        NewsScreenUi(
            state = state,
            proceedIntent = proceedIntent
        )
    }

    @Composable
    private fun NewsScreenUi(
        state: NewsScreenState,
        proceedIntent: (INewsScreenIntent) -> Unit
    ) {

        Column {

            AppSearchBar(
                active = state.isSearchBarActive,
                query = state.query,
                history = state.history,
                onQueryChanged = {
                    proceedIntent(
                        INewsScreenIntent.UpdateQuery(it)
                    )
                },
                onSearch = {
                    proceedIntent(
                        INewsScreenIntent.LoadByQuery(it)
                    )
                },
                onActiveChanged = {
                    proceedIntent(
                        INewsScreenIntent.UpdateIsSearchBarActive(it)
                    )
                }
            )

            NewsTopicsRow(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
                queryText = state.query,
                selectedTopic = state.selectedTopic,
                allTopics = state.listOfTopics,
                proceedIntent = proceedIntent
            )

            when {
                state.isLoading ->
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                state.articles.isEmpty() && !state.isLoading ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Nothing, reload"
                        )
                        Button(
                            content = {
                                Text(
                                    text = "reload"
                                )
                            },
                            onClick = {
                                proceedIntent(
                                    INewsScreenIntent.RetryLoadByQuery
                                )
                            }
                        )
                    }

                else ->
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(state.articles) { article ->

                            ArticlePlaceholder(
                                modifier = Modifier
                                    .padding(vertical = 10.dp, horizontal = 20.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(25.dp)
                                    )
                                    .widthIn(max = 700.dp)
                                    .padding(vertical = 15.dp, horizontal = 20.dp)
                                    .clickable {
                                        proceedIntent(
                                            INewsScreenIntent.NavigateToDetails(
                                                article = article
                                            )
                                        )
                                    },
                                article = article
                            )
                        }
                    }
            }
        }
    }
}
