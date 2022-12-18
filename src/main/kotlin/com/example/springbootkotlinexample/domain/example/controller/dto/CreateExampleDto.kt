package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractCreateDto
import com.example.springbootkotlinexample.domain.example.entity.Example
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class CreateExampleDto(
    @field:NotNull
    @field:Size(min = 2, max = 5)
    val name: String?,

    @field:NotNull
    @field:Size(min = 2, max = 100)
    val content: String?,

    @field:NotNull
    @field:Max(100)
    val count: Int?,
) : AbstractCreateDto<Example>() {
    override fun toEntity(): Example {
        return Example(
            name = name!!,
            content = content!!,
            count = count!!,
        )
    }
}