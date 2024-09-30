package com.example.duncanclark.datasource.mapper

import com.example.duncanclark.domain.model.ListItem
import com.example.duncanclark.domain.model.RemoteListItem
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteToListItemMapperImplTest {

    // Stubs
    private val stubId = 1L
    private val stubListId = 2
    private val stubName = "hello"

    private lateinit var subject: RemoteToListItemMapperImpl

    @Before
    fun before() {
        subject = RemoteToListItemMapperImpl()
    }

    @Test
    fun `Given a list, When invoke, Then return expected list`() = runTest {
        val responseData = listOf(RemoteListItem(stubId, stubListId, stubName))
        val expected = listOf(ListItem.Item(stubId, stubListId, stubName))

        val actual = subject(responseData)

        assertEquals(expected, actual)
    }

    @Test
    fun `Given an empty list, When invoke, Then return an empty list`() = runTest {
        val responseData = emptyList<RemoteListItem>()
        val expected = emptyList<ListItem>()

        val actual = subject(responseData)

        assertEquals(expected, actual)
    }

    @Test
    fun `Given a list with duplicates, When invoke, Then return a distinct list`() = runTest {
        val responseData = listOf(
            RemoteListItem(stubId, stubListId, stubName),
            RemoteListItem(380, 1, "Goodbye"),
            RemoteListItem(stubId, stubListId, stubName),
        )
        val expected = listOf(
            ListItem.Item(stubId, stubListId, stubName),
            ListItem.Item(380, 1, "Goodbye"),
        )

        val actual = subject(responseData)

        assertEquals(expected, actual)
    }

    @Test
    fun `Given a list with null & empty names, When invoke, Then return a list without empty & null names`() = runTest {
        val responseData = listOf(
            RemoteListItem(stubId, stubListId, stubName),
            RemoteListItem(380, 1, "Goodbye"),
            RemoteListItem(450, stubListId, null),
            RemoteListItem(230, 1, "Hello again"),
            RemoteListItem(2, stubListId, ""),
        )
        val expected = listOf(
            ListItem.Item(stubId, stubListId, stubName),
            ListItem.Item(380, 1, "Goodbye"),
            ListItem.Item(230, 1, "Hello again"),
        )

        val actual = subject(responseData)

        assertEquals(expected, actual)
    }
}