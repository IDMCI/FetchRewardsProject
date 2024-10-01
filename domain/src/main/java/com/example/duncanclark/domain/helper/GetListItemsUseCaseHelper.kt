package com.example.duncanclark.domain.helper

import com.example.duncanclark.domain.model.ListItem
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.model.groupByListIdThenName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetListItemsUseCaseHelper{
    operator fun invoke(input: Flow<Result<ListItems>>): Flow<Result<ListItems>>
}

class GetListItemsUseCaseHelperImpl @Inject constructor(): GetListItemsUseCaseHelper {
    override operator fun invoke(input: Flow<Result<ListItems>>): Flow<Result<ListItems>> {
        return input.map { result ->
            result.map { listItems ->
                val items = listItems.filterIsInstance<ListItem.Item>()
                val sortedItems = items.groupByListIdThenName()
                sortedItems
            }
        }
    }
}