package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseUpdateDto
import com.example.springbootkotlinexample.domain.example.entity.Example

data class UpdateExampleDto(
    val name: String?
) : BaseUpdateDto<Example>() {
    override fun toEntity(entity: Example): Example =
        entity.copy(
            id = id ?: entity.id,
            name = name ?: entity.name
        )
}