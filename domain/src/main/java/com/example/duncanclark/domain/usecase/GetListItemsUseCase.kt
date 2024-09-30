package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.repository.ListItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListItemsUseCase @Inject constructor(
    private val repository: ListItemsRepository<Flow<Result<ListItems>>>
): UseCase<ListItems> {
    override suspend fun execute(): Flow<Result<ListItems>> {
        return repository.getData()
    }
}