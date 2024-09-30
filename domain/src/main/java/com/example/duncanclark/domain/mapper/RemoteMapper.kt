package com.example.duncanclark.domain.mapper

interface RemoteMapper <T, R> {
    operator fun invoke(input: T): R
}
