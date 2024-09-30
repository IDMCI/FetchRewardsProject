package com.example.duncanclark.common.mapper

interface Mapper <T, R> {
    operator fun invoke(input: T): R
}