package com.example.springbootkotlinexample.domain.brand.controller.dto

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand

data class CreateBrandDto(
    val name: String,
    val description: String,
) : BaseCreateDto<Brand>() {
    override fun toEntity(): Brand = Brand(
        name = name,
        description = description,
    )
}
