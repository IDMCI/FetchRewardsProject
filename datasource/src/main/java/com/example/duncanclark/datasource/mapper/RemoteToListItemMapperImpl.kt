package com.example.duncanclark.datasource.mapper

import com.example.duncanclark.common.mapper.Mapper
import com.example.duncanclark.domain.model.ListItem
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.model.RemoteListItems
import com.example.duncanclark.domain.model.removeUnknownNames
import javax.inject.Inject

class RemoteToListItemMapperImpl @Inject constructor(): Mapper<RemoteListItems, ListItems> {
    override operator fun invoke(input: RemoteListItems): ListItems {
        return input
            .removeUnknownNames() // Removes empty & null named items from list.
            .map {
                ListItem.Item(
                    id = it.id,
                    listId = it.listId,
                    name = it.name!!,
                )
            }
            .distinctBy { it.id } // Remove duplicate items just in case.
    }
}