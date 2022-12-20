package com.example.springbootkotlinexample.domain.product.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.entity.AbstractAuditingEntity
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "tb_product")
class Product(
    name: String,
) : AbstractAuditingEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    override fun <UD : AbstractUpdateDto<E>, E> update(dto: UD) {
        val updateProductDto = dto as UpdateProductDto
        this.name = updateProductDto.name ?: this.name
    }
}
