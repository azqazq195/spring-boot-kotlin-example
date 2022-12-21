package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractReadDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.ReadBrandDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import java.time.LocalDateTime

class ReadProductDto(
    val id: Long?,
    val name: String,
    val price: Number,
    val brand: ReadBrandDto,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    val deletedAt: LocalDateTime?
) : AbstractReadDto<Product>()