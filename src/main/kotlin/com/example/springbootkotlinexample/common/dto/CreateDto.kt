package com.example.springbootkotlinexample.common.dto

abstract class CreateDto<T> {
    abstract fun toEntity(): T
}