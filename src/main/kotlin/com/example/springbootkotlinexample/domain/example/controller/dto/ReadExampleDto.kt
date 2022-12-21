package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractReadDto
import com.example.springbootkotlinexample.domain.example.entity.Example
import com.example.springbootkotlinexample.domain.example.entity.constants.ExampleEnum

class ReadExampleDto(
    val name: String,
    val content: String,
    val count: Int,
    val enum: ExampleEnum
) : AbstractReadDto<Example>()
