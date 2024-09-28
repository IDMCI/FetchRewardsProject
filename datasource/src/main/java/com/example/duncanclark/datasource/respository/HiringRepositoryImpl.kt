package com.example.duncanclark.datasource.respository

import com.example.duncanclark.datasource.model.RemoteHiringModel
import com.example.duncanclark.datasource.remote.RemoteHiringService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class HiringRepositoryImpl @Inject constructor(
    private val hiringService: RemoteHiringService
): HiringRepository {
    override suspend fun getHiringList(): Flow<Result<List<RemoteHiringModel>>> = flow {
        try {
            val result = hiringService.getHiringData().await()
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}