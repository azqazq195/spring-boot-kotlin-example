package com.example.springbootkotlinexample.common.base.controller.dto

import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity
import com.example.springbootkotlinexample.common.base.validator.NotNullWhenBulk

abstract class AbstractUpdateDto<E: PrimaryKeyEntity>(
    @field:NotNullWhenBulk
    val id: Long? = null
)