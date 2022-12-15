package com.example.springbootkotlinexample.common.generic.controller.dto

abstract class BaseUpdateDto<T> {
    val id: Long? = null
    abstract fun toEntity(entity: T): T
}