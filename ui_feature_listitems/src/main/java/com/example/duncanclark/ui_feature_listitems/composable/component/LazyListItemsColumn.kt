package com.example.duncanclark.ui_feature_listitems.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duncanclark.domain.model.ListItems

@Composable
fun LazyListItemsColumn(
    modifier: Modifier,
    listItems: ListItems,
    onClick: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 6.dp, vertical = 12.dp)
    ) {
        items(listItems) {
            ListItemTreatment(
                Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                it,
                onClick,
            )
        }
    }
}