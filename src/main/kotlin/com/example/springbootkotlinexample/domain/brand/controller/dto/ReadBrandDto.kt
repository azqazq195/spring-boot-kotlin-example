package com.example.springbootkotlinexample.domain.brand.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractReadDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import java.time.LocalDateTime

class ReadBrandDto(
    val id: Long?,
    val name: String,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    val deletedAt: LocalDateTime?
) : AbstractReadDto<Brand>()
