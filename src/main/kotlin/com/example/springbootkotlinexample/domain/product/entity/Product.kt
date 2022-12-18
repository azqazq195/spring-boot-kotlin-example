package com.example.springbootkotlinexample.domain.product.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.entity.*
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import jakarta.persistence.*

@Entity(name = "tb_product")
class Product(
    name: String,
    description: String,
) : AbstractAuditingEntity() {
    var name: String = name
        private set

    var description: String = description
        private set

    override fun <UD : AbstractUpdateDto<E>, E> update(dto: UD) {
        val updateProductDto = dto as UpdateProductDto
        this.name = updateProductDto.name ?: this.name
        this.description = updateProductDto.description ?: this.description
    }
}