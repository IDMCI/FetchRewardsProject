package com.example.duncanclark.datasource.module

import com.example.duncanclark.datasource.mapper.RemoteToListItemMapperImpl
import com.example.duncanclark.datasource.remote.RemoteHiringService
import com.example.duncanclark.datasource.respository.ListItemsRepositoryImpl
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.repository.ListItemsRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourcedModule {

    @Singleton
    @Provides
    @Named("ListItemsRepositoryImpl")
    fun provideListItemsRepository(
        hiringService: RemoteHiringService,
        mapper: RemoteToListItemMapperImpl,
    ): ListItemsRepository<Flow<Result<ListItems>>> {
        return ListItemsRepositoryImpl(hiringService, mapper)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideRemoteHiringService(
        retrofit: Retrofit,
    ): RemoteHiringService = retrofit.create(RemoteHiringService::class.java)

    @Reusable
    @Provides
    @Named("RemoteToListItemMapper")
    fun provideRemoteToListItemMapper() = RemoteToListItemMapperImpl()
}