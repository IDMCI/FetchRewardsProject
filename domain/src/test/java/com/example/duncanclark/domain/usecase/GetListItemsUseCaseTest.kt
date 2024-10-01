package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.helper.GetListItemsUseCaseHelperImpl
import com.example.duncanclark.domain.model.ListItem
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.repository.ListItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetListItemsUseCaseTest {

    // Mocks
    private val mockRepository: ListItemsRepository<Flow<Result<ListItems>>> = mock()
    private val mockHelper: GetListItemsUseCaseHelperImpl = mock()

    // Stubs
    private val stubId = 1L
    private val stubListId = 2
    private val stubName = "hello"

    private lateinit var subject: GetListItemsUseCase

    @Before
    fun before() {
        subject = GetListItemsUseCase(
            mockRepository,
            mockHelper,
        )
    }

    @Test
    fun `When execute, Then return expected list`() = runTest {
        val data = listOf(ListItem.Item(stubId, stubListId, stubName))
        val expected = flowOf(Result.success(data))

        whenever(mockRepository.getData()).thenReturn(expected)
        whenever(mockHelper.invoke(expected)).thenReturn(expected)

        val actual = subject.execute()

        assertEquals(expected, actual)
    }
}