package com.example.springbootkotlinexample.domain.brand.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand

class UpdateBrandDto(
    val name: String?,
) : AbstractUpdateDto<Brand>()
