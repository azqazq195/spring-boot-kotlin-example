package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.service.exception.RequiredArgumentException
import com.example.springbootkotlinexample.common.base.controller.validator.NotNullWhenBulk
import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto

class UpdateProductDto(
    @field:NotNullWhenBulk
    val id: Long? = null,
    val name: String?,
    val price: Int?,
    val brandId: Long?,
) : IUpdateDto {
    override fun getId(): Long {
        if (id == null) throw RequiredArgumentException("id")
        return id
    }
}
