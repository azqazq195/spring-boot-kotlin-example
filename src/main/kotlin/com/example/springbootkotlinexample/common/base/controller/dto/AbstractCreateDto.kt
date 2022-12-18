package com.example.springbootkotlinexample.common.base.controller.dto

import com.example.springbootkotlinexample.common.base.entity.IEntity

abstract class AbstractCreateDto<E: IEntity> {
    open fun toEntity(): E {
        TODO("Not yet implemented")
    }
}