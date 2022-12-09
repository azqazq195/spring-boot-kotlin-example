package com.example.springbootkotlinexample.common.dto

abstract class UpdateDto<T> {
    abstract fun toUpdatedEntity(entity: T): T
}