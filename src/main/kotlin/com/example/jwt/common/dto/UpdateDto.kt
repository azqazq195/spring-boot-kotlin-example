package com.example.jwt.common.dto

abstract class UpdateDto<T> {
    abstract fun toUpdatedEntity(entity: T): T
}