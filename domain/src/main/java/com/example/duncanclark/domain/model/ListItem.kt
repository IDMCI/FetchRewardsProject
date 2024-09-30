package com.example.duncanclark.domain.model

typealias ListItems = List<ListItem>

sealed class ListItem {
    data class Item(
        val id: Long,
        val listId: Int,
        val name: String
    ): ListItem()
}