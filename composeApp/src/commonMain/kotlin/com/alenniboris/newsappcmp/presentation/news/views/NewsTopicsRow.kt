package com.alenniboris.newsappcmp.presentation.news.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alenniboris.newsappcmp.presentation.news.INewsScreenIntent
import com.alenniboris.newsappcmp.presentation.news.Topic

@Composable
fun NewsTopicsRow(
    modifier: Modifier = Modifier,
    queryText: String,
    selectedTopic: Topic?,
    allTopics: List<Topic>,
    itemsLazyListState: LazyListState = rememberLazyListState(),
    proceedIntent: (INewsScreenIntent) -> Unit
) {

    LaunchedEffect(key1 = selectedTopic) {
        val index = allTopics.indexOfFirst { it == selectedTopic }
        if (index >= 0) {
            itemsLazyListState.animateScrollToItem(index)
        }
    }

    LazyRow(
        modifier = modifier,
        state = itemsLazyListState
    ) {
        itemsIndexed(allTopics) { index, element ->
            TopicItem(
                modifier = Modifier
                    .animateItem(fadeInSpec = null, fadeOutSpec = null)
                    .padding(
                        if (index == 0) PaddingValues(top = 10.dp, bottom = 10.dp, end = 10.dp)
                        else PaddingValues(10.dp)
                    ),
                title = element.text,
                selected = selectedTopic?.id == index || queryText == element.text,
                onClick = {
                    proceedIntent(
                        INewsScreenIntent.OnTopicClicked(element)
                    )
                }
            )
        }
    }
}

@Composable
private fun TopicItem(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background =
        if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary

    val textColor = if (selected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimary

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(background)
            .clickable { onClick() },
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = title,
            color = textColor,
            fontSize = 14.sp
        )
    }
}