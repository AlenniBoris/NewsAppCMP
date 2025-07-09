package com.alenniboris.newsappcmp.presentation.model

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import java.text.SimpleDateFormat
import java.util.Locale

data class ArticleModelUi(
    val domainModel: ArticleModelDomain
) {

    val localTimeText: String
        get() {
            val date = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.getDefault()
            ).parse(domainModel.publishedAt)
            return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date.time)
        }

}

fun ArticleModelDomain.toUiModel(): ArticleModelUi =
    ArticleModelUi(
        domainModel = this
    )