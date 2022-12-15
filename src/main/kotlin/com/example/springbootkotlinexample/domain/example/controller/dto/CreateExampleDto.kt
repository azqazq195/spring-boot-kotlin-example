package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.domain.example.entity.Example

data class CreateExampleDto(
    val name: String
) : BaseCreateDto<Example>() {
    override fun toEntity(): Example =
        Example(name = name)
}