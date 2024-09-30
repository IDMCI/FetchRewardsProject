package com.example.duncanclark.datasource.respository

import com.example.duncanclark.datasource.remote.RemoteHiringService
import com.example.duncanclark.domain.mapper.RemoteMapper
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.model.RemoteListItems
import com.example.duncanclark.domain.repository.ListItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class ListItemsRepositoryImpl @Inject constructor(
    private val hiringService: RemoteHiringService,
    private val mapper: RemoteMapper<RemoteListItems, ListItems>
): ListItemsRepository<Flow<Result<ListItems>>> {
    override suspend fun getData(): Flow<Result<ListItems>> = flow {
        try {
            val result = hiringService.getItemListData().await()
            emit(Result.success(mapper(result)))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}