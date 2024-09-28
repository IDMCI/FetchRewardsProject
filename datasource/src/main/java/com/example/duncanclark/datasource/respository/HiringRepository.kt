package com.example.duncanclark.datasource.respository

import com.example.duncanclark.datasource.model.RemoteHiringModel
import kotlinx.coroutines.flow.Flow

interface HiringRepository {
    suspend fun getHiringList(): Flow<Result<List<RemoteHiringModel>>>
}