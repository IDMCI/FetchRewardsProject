package com.example.duncanclark.domain.repository

interface ListItemsRepository<T> {
    suspend fun getData(): T
}