package com.alenniboris.newsappcmp.presentation.screens.details.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.AsyncImage
import com.alenniboris.newsappcmp.domain.util.GsonUtil.fromJson
import com.alenniboris.newsappcmp.presentation.model.ArticleModelUi
import com.alenniboris.newsappcmp.presentation.screens.details.DetailsScreenState
import com.alenniboris.newsappcmp.presentation.screens.details.DetailsScreenViewModel
import com.alenniboris.newsappcmp.presentation.screens.details.IDetailsScreenEvent
import com.alenniboris.newsappcmp.presentation.screens.details.IDetailsScreenIntent
import com.alenniboris.newsappcmp.presentation.utils.views.AppTopBar
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import newsappcmp.composeapp.generated.resources.Res
import newsappcmp.composeapp.generated.resources.author_text
import newsappcmp.composeapp.generated.resources.back_icon
import newsappcmp.composeapp.generated.resources.image_description
import newsappcmp.composeapp.generated.resources.liked_icon
import newsappcmp.composeapp.generated.resources.not_liked_icon
import newsappcmp.composeapp.generated.resources.nothing_found_text
import newsappcmp.composeapp.generated.resources.published_text
import newsappcmp.composeapp.generated.resources.source_text
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf


data class DetailsScreen(
    val articleJson: String
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current
        val viewModel =
            koinInject<DetailsScreenViewModel> { parametersOf(articleJson.fromJson<ArticleModelUi>()) }
        val state by viewModel.state.collectAsStateWithLifecycle()
        val proceedIntent by remember { mutableStateOf(viewModel::proceedIntent) }
        val event by remember { mutableStateOf(viewModel.event) }

        LaunchedEffect(event) {
            launch {
                event.filterIsInstance<IDetailsScreenEvent.NavigateBack>().collect {
                    navigator?.pop()
                }
            }
        }

        DetailsScreenUi(
            state = state,
            proceedIntent = proceedIntent
        )
    }

    @Composable
    private fun DetailsScreenUi(
        state: DetailsScreenState,
        proceedIntent: (IDetailsScreenIntent) -> Unit
    ) {

        Column(
            modifier = Modifier
                .padding(PaddingValues(top = 30.dp))
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {

            AppTopBar(
                modifier = Modifier
                    .padding(PaddingValues(horizontal = 20.dp, vertical = 15.dp))
                    .fillMaxWidth(),
                leftBtnPainter = painterResource(Res.drawable.back_icon),
                onLeftBtnClicked = {
                    proceedIntent(
                        IDetailsScreenIntent.NavigateBack
                    )
                },
                rightBtnPainter = painterResource(
                    if (state.isLiked) Res.drawable.liked_icon
                    else Res.drawable.not_liked_icon
                ),
                onRightBtnClicked = {
                    proceedIntent(IDetailsScreenIntent.ProceedLikedAction)
                }
            )

            when {
                state.article == null -> {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = stringResource(Res.string.nothing_found_text)
                        )
                    }
                }

                else -> {

                    val article = state.article

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(PaddingValues(horizontal = 20.dp))
                            .padding(horizontal = 24.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {

                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .clip(RoundedCornerShape(24.dp)),
                            model = state.article.domainModel.urlToImage,
                            contentDescription = stringResource(Res.string.image_description),
                            contentScale = ContentScale.FillWidth
                        )

                        Text(
                            text = stringResource(Res.string.author_text) + article.domainModel.author,
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(Res.string.source_text) + article.domainModel.source.name,
                            fontSize = 20.sp,
                        )
                        Text(
                            text = "url: ${article.domainModel.url}",
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(Res.string.published_text)
                                    + article.localTimeText,
                            fontSize = 20.sp
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 20.dp),
                            text = article.domainModel.content,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}
