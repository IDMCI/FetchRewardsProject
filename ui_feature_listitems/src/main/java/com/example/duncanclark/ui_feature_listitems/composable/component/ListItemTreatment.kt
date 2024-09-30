package com.example.duncanclark.ui_feature_listitems.composable.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.duncanclark.domain.model.ListItem

@Composable
fun ListItemTreatment(
    modifier: Modifier,
    listItem: ListItem,
    onClick: (Long) -> Unit,
) {
    when (listItem) {
        is ListItem.Item -> {
            Text(
                modifier = modifier.clickable {
                    onClick(listItem.id)
                },
                text = listItem.name,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        // Just in case a type is added and needs specific UI treatment.
        else -> Text(
            modifier = modifier,
            text = "Unknown type with id: ${listItem.id}",
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.error,
        )
    }
}