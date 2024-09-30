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