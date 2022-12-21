package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.domain.product.entity.Product

class UpdateProductDto(
    val name: String?,
    val price: Int?,
    val brandId: Long?,
) : AbstractUpdateDto<Product>()
