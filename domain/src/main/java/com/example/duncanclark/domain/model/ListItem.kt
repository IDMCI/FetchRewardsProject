package com.example.duncanclark.domain.model

typealias ListItems = List<ListItem>

sealed class ListItem {
    open var id: Long = -1L

    data class Item(
        override var id: Long,
        val listId: Int,
        val name: String
    ): ListItem()
}

// TODO DC: Ask product manager if the desired sort order is alphabetically or numerically.
/**
 * Sorting Requirements:
 * - Display all the items grouped by "listId".
 * - Sort the results first by "listId" then by "name" when displaying.
 * - Filter out any items where "name" is blank or null.
 */
fun List<ListItem.Item>.groupByListIdThenName(): ListItems {
    val myGroup = this.groupBy { it.listId }
        .flatMap { (_, group) ->
            group.sortedBy { it.name }
        }
        .sortedBy { it.listId }
    return myGroup
}