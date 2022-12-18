package com.example.springbootkotlinexample.common.base.controller.dto

import com.example.springbootkotlinexample.common.base.validator.NotNullWhenBulk

abstract class AbstractUpdateDto(
    @field:NotNullWhenBulk
    val id: Long?
)