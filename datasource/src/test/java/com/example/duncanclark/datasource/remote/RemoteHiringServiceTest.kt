package com.example.duncanclark.datasource.remote

import com.example.duncanclark.datasource.model.RemoteHiringModel
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.junit.Assert.assertEquals
import retrofit2.await
import retrofit2.http.GET

class RemoteHiringServiceTest {

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    private lateinit var subject: RemoteHiringService

    @Before
    fun before() {
        okHttpClient = TestOkHttpBuilder().getOkHttpBuilder()
        retrofit = TestRetrofitBuilder().getRetrofitBuilder(okHttpClient)
        subject = TestRemoteHiringService(retrofit)
    }

    @Test
    fun `Given Remote API,  When getHiringData, Then Return json`() = runTest {
        val expected = RemoteHiringModel(
            id = 755,
            listId = 2,
            name = ""
        )

        try {
            val results = subject.getHiringData().await()
            val actual = results.first()

            assertEquals(expected, actual)
        } catch (e: Exception) {
            throw(e)
        }
    }

    inner class TestRemoteHiringService(
        private val retrofit: Retrofit
    ): RemoteHiringService {
        private val _service by lazy { retrofit.create(RemoteHiringService::class.java) }

        @GET("hiring.json")
        override fun getHiringData(): Call<List<RemoteHiringModel>> =
            _service.getHiringData()
    }

    inner class TestOkHttpBuilder() {
        fun getOkHttpBuilder(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
    }

    inner class TestRetrofitBuilder() {
        fun getRetrofitBuilder(
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}