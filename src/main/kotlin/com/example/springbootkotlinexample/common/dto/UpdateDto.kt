package com.example.springbootkotlinexample.common.dto

abstract class UpdateDto<T> {
    abstract fun toEntity(entity: T): T
}