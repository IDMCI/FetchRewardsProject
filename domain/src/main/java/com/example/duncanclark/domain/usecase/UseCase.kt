package com.example.duncanclark.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<T> {
    suspend fun execute(): Flow<Result<T>>
}