package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseUpdateDto
import com.example.springbootkotlinexample.domain.product.entity.Product

data class UpdateProductDto(
    val name: String?
) : BaseUpdateDto<Product>() {
    override fun toEntity(entity: Product): Product =
        entity.copy(
            id = id ?: entity.id,
            name = name ?: entity.name
        )
}