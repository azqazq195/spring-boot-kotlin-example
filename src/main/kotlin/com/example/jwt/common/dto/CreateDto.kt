package com.example.jwt.common.dto

abstract class CreateDto<T> {
    abstract fun toEntity(): T
}