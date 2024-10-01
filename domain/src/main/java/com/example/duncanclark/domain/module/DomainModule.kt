package com.example.duncanclark.domain.module

import com.example.duncanclark.domain.helper.GetListItemsUseCaseHelper
import com.example.duncanclark.domain.helper.GetListItemsUseCaseHelperImpl
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.repository.ListItemsRepository
import com.example.duncanclark.domain.usecase.GetListItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Reusable
    @Provides
    fun provideGetListItemsUseCaseHelperImpl(): GetListItemsUseCaseHelper =
        GetListItemsUseCaseHelperImpl()

    @Singleton
    @Provides
    fun provideGetListItemsUseCase(
        @Named("ListItemsRepositoryImpl") listItemsRepository:
            ListItemsRepository<Flow<Result<ListItems>>>,
        helper: GetListItemsUseCaseHelper,
    ) = GetListItemsUseCase(listItemsRepository, helper)
}