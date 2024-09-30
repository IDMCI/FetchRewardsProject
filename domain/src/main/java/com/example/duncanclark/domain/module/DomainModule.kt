package com.example.duncanclark.domain.module

import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.repository.ListItemsRepository
import com.example.duncanclark.domain.usecase.GetListItemsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideGetListItemsUseCase(
        listItemsRepository: ListItemsRepository<Flow<Result<ListItems>>>
    ) = GetListItemsUseCaseImpl(listItemsRepository)
}