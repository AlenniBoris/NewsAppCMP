package com.alenniboris.newsappcmp.presentation.utils.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import newsappcmp.composeapp.generated.resources.Res
import newsappcmp.composeapp.generated.resources.close_icon
import newsappcmp.composeapp.generated.resources.history_icon
import newsappcmp.composeapp.generated.resources.search_icon
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    active: Boolean,
    query: String,
    history: Set<String>,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
) {
    val colors1 = SearchBarDefaults.colors(
        containerColor = MaterialTheme.colorScheme.primary,
        inputFieldColors = TextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary
        )
    )
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChanged,
                onSearch = onSearch,
                expanded = active,
                onExpandedChange = onActiveChanged,
                placeholder = {
                    Text(text = "Enter query")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.search_icon),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "search"
                    )
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            painter = painterResource(Res.drawable.close_icon),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = "close",
                            modifier = Modifier.clickable {
                                if (query.isNotBlank()) {
                                    onQueryChanged("")
                                } else {
                                    onActiveChanged(false)
                                }
                            }
                        )
                    }
                },
                colors = colors1.inputFieldColors,
            )
        },
        expanded = active,
        onExpandedChange = onActiveChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .padding(horizontal = if (active) 0.dp else 24.dp),
        shape = SearchBarDefaults.inputFieldShape,
        colors = colors1,
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = SearchBarDefaults.windowInsets,
        content = {
            LazyRow {
                items(items = history.toList()) {
                    HistoryIcon(onQueryChanged = onQueryChanged, text = it)
                }
            }
        },
    )
}

@Composable
fun HistoryIcon(
    onQueryChanged: (String) -> Unit,
    text: String
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onQueryChanged(text) }
    ) {
        Icon(
            modifier = Modifier.padding(end = 16.dp),
            painter = painterResource(Res.drawable.history_icon),
            contentDescription = "history"
        )
        Text(text = text)
    }
}