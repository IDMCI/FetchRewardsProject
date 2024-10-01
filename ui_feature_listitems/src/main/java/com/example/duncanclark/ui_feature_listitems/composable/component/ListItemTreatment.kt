package com.example.duncanclark.ui_feature_listitems.composable.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duncanclark.domain.model.ListItem

@Composable
fun ListItemTreatment(
    modifier: Modifier,
    listItem: ListItem,
    onClick: (Long) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
            .clickable {
                onClick(listItem.id)
            }
    ) {
        when (listItem) {
            is ListItem.Item -> {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        // Name
                        Text(
                            modifier = Modifier.weight(1F),
                            text = listItem.name,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        // ID
                        Text(
                            text = "#${listItem.id}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    }
                    // ListID
                    Text(
                        text = "ListID: ${listItem.listId}",
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
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
}