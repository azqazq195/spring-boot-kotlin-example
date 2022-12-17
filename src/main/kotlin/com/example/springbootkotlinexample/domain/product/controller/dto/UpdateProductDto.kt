package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 * 상품 수정 DTO
 * @property name 상품명: null 허용하지 않음, 최소 2자, 최대 5자
 * @property description 상품 설명: null 허용하지 않음, 최소 2자, 최대 100자
 */
data class UpdateProductDto(
    override val id: Long?,

    @field:NotEmpty
    @field:Size(min = 2, max = 5)
    val name: String?,

    @field:NotEmpty
    @field:Size(min = 2, max = 100)
    val description: String?,
): IUpdateDto<Product>