package com.example.springbootkotlinexample.common.base.controller.dto

import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity

abstract class AbstractCreateDto<E: PrimaryKeyEntity> {
    abstract fun toEntity(): E
}