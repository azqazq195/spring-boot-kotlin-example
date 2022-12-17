package com.example.springbootkotlinexample.domain.product.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.ICreateDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * 상품 생성 DTO
 * @property name 상품명: null 허용하지 않음, 최소 2자, 최대 5자
 * @property description 상품 설명: null 허용하지 않음, 최소 2자, 최대 100자
 */
// String? 을 허용해 주지 않으면 DTO 생성자체가 오류난다.
// null을 허용해주되 NotNull로 validation을 걸어주자.
data class CreateProductDto(
    @field:NotNull
    @field:Size(min = 2, max = 5)
    val name: String?,

    @field:NotNull
    @field:Size(min = 2, max = 100)
    val description: String?,
) : ICreateDto<Product> {
    override fun toEntity(): Product {
        return Product(
            name = name!!,
            description = description!!,
        )
    }
}