package com.example.springbootkotlinexample.common.generic.controller.dto

abstract class BaseCreateDto<T> {
    abstract fun toEntity(): T
}