package com.example.duncanclark.datasource.respository

import com.example.duncanclark.datasource.remote.RemoteHiringService
import com.example.duncanclark.domain.mapper.RemoteMapper
import com.example.duncanclark.domain.model.ListItem
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.model.RemoteListItem
import com.example.duncanclark.domain.model.RemoteListItems
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.mock.Calls

class ListItemsRepositoryImplTest {

    // Mocks
    private val mockMapper: RemoteMapper<RemoteListItems, ListItems> = mock()

    // Mockks
    private val mockkService: RemoteHiringService = mockk()

    // Stubs
    private val stubId = 1L
    private val stubListId = 2
    private val stubName = "hello"

    private lateinit var subject: ListItemsRepositoryImpl

    @Before
    fun before() {
        subject = ListItemsRepositoryImpl(
            mockkService,
            mockMapper,
        )
    }

    @Test
    fun `Given a successful call, When getHiringList, Then emit success`() = runTest {
        val responseData = listOf(RemoteListItem(stubId, stubListId, stubName))
        val transformedData = listOf(ListItem.Item(stubId, stubListId, stubName))
        val call: Call<List<RemoteListItem>> = Calls.response(responseData)
        val expected = Result.success(transformedData)

        whenever(mockMapper.invoke(responseData)).thenReturn(transformedData)
        coEvery { mockkService.getItemListData() }.returns(call)

        val actual = mutableListOf<Result<ListItems>>()

        subject.getData().toList(actual)

        assertEquals(expected, actual.first())
    }
}