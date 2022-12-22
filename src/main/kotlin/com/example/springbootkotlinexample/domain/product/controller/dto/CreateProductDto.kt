package com.example.springbootkotlinexample.domain.product.controller.dto

import jakarta.validation.constraints.NotNull

data class CreateProductDto(
    @field:NotNull
    val name: String?,

    @field:NotNull
    val price: Int?,

    @field:NotNull
    val brandId: Long?,
)
