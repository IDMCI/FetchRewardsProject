package com.example.duncanclark.datasource.respository

import com.example.duncanclark.datasource.model.RemoteHiringModel
import com.example.duncanclark.datasource.remote.RemoteHiringService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.mock.Calls

class HiringRepositoryImplTest {

    // Mockks
    private val mockkService: RemoteHiringService = mockk()

    private lateinit var subject: HiringRepository

    @Before
    fun before() {
        subject = HiringRepositoryImpl(mockkService)
    }

    @Test
    fun `Given a successful call, When getHiringList, Then emit success`() = runTest {
        val hiringList = listOf(RemoteHiringModel(1L, 2, "hello"))
        val call: Call<List<RemoteHiringModel>> = Calls.response(hiringList)
        val expected = Result.success(hiringList)

        coEvery { mockkService.getHiringData() }.returns(call)
        val actual = mutableListOf<Result<List<RemoteHiringModel>>>()

        subject.getHiringList().toList(actual)

        assertEquals(expected, actual.first())
    }
}