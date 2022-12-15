package com.example.springbootkotlinexample.domain.brand.controller.dto

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseUpdateDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand

data class UpdateBrandDto(
    val name: String?,
    val description: String?,
) : BaseUpdateDto<Brand>() {
    override fun toEntity(entity: Brand): Brand =
        entity.copy(
            id = id ?: entity.id,
            name = name ?: entity.name,
            description = description ?: entity.description,
        )
}