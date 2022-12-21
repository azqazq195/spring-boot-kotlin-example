package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractCreateDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.product.entity.Product
import jakarta.validation.constraints.NotNull

class CreateProductDto(
    @field:NotNull
    val name: String?,

    @field:NotNull
    val price: Int?,

    @field:NotNull
    val brandId: Long?,
) : AbstractCreateDto<Product>() {
    override fun toEntity(): Product {
        return Product(
            name = name!!,
            price = price!!
        )
    }
}
