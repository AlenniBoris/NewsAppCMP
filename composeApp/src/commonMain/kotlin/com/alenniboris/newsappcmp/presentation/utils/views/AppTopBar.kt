package com.alenniboris.newsappcmp.presentation.utils.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import newsappcmp.composeapp.generated.resources.Res
import newsappcmp.composeapp.generated.resources.top_bar_left_btn_description
import newsappcmp.composeapp.generated.resources.top_bar_right_btn_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    headerTextString: String = "",
    textAlignment: Alignment = Alignment.Center,
    isLeftBtnAnimated: Boolean = false,
    leftBtnPainter: Painter? = null,
    onLeftBtnClicked: () -> Unit = {},
    isRightBtnAnimated: Boolean = false,
    rightBtnPainter: Painter? = null,
    onRightBtnClicked: () -> Unit = {},
    content: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        leftBtnPainter?.let {
            AppIconButton(
                modifier = Modifier.padding(end = 10.dp),
                isAnimated = isLeftBtnAnimated,
                iconPainter = leftBtnPainter,
                onClick = onLeftBtnClicked,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(Res.string.top_bar_left_btn_description)
            )
        }

        content?.let { content ->
            content()
        } ?: Text(
            modifier = Modifier.weight(1f),
            text = headerTextString,
            color = MaterialTheme.colorScheme.onPrimary,
        )


        rightBtnPainter?.let {
            AppIconButton(
                modifier = Modifier.padding(start = 10.dp),
                isAnimated = isRightBtnAnimated,
                iconPainter = rightBtnPainter,
                onClick = onRightBtnClicked,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(Res.string.top_bar_right_btn_description)
            )
        }

    }
}