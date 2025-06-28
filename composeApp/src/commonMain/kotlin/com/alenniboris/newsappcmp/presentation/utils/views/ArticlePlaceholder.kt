package com.alenniboris.newsappcmp.presentation.utils.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain


@Composable
fun ArticlePlaceholder(
    modifier: Modifier,
    article: ArticleModelDomain
) {

    Column(
        modifier = modifier
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp)),
            model = article.urlToImage,
            contentDescription = "image",
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = article.source.name,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = article.title,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = article.description ?: "",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 15.dp)
        )
    }
}