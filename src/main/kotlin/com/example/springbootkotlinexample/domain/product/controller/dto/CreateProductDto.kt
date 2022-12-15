package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.domain.product.entity.Product

data class CreateProductDto(
    val name: String
) : BaseCreateDto<Product>() {
    override fun toEntity(): Product =
        Product(name = name)
}