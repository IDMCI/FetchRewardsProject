package com.example.duncanclark.ui_feature_listitems.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.duncanclark.domain.model.ListItems

@Composable
fun LazyListItemsColumn(
    modifier: Modifier,
    listItems: ListItems,
    onClick: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(listItems) {
            ListItemTreatment(
                Modifier.fillMaxWidth(),
                it,
                onClick,
            )
        }
    }
}