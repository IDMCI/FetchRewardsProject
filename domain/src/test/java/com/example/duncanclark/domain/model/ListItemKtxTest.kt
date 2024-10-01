package com.example.duncanclark.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ListItemKtxTest {
    private val myList = listOf(
        ListItem.Item(1, 1, "Item 10"),
        ListItem.Item(2, 3, "Item 201"),
        ListItem.Item(3, 2, "Item 3002"),
        ListItem.Item(4, 2, "Item 302"),
        ListItem.Item(5, 1, "Item 1"),
    )

    @Test
    fun `Given ListItems, When groupByListIdThenName, Return expected order`() {
        val expected = listOf(
            ListItem.Item(5, 1, "Item 1"),
            ListItem.Item(1, 1, "Item 10"),
            ListItem.Item(3, 2, "Item 3002"),
            ListItem.Item(4, 2, "Item 302"),
            ListItem.Item(2, 3, "Item 201"),
        )

        val actual = myList.groupByListIdThenName()

        assertEquals(expected, actual)
    }
}