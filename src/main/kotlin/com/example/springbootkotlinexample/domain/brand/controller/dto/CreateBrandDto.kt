package com.example.springbootkotlinexample.domain.brand.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractCreateDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import jakarta.validation.constraints.NotNull

class CreateBrandDto(
    @field:NotNull
    val name: String?,
) : AbstractCreateDto<Brand>() {
    override fun toEntity(): Brand {
        return Brand(
            name = name!!,
        )
    }
}
