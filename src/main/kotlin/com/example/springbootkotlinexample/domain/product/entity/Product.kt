package com.example.springbootkotlinexample.domain.product.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractReadDto
import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.entity.AbstractAuditingEntity
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.product.controller.dto.ReadProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "tb_product")
class Product(
    name: String,
    price: Int,
) : AbstractAuditingEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    @Column(nullable = false)
    var price: Int = price
        private set

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    lateinit var brand: Brand
        private set

    fun setBrand(brand: Brand) {
        this.brand = brand
//        brand.addProduct(this)
    }

    override fun <UD : AbstractUpdateDto<E>, E> update(dto: UD) {
        val updateProductDto = dto as UpdateProductDto
        this.name = updateProductDto.name ?: this.name
        this.price = updateProductDto.price ?: this.price
    }

    override fun <RD : AbstractReadDto<E>, E> toReadDto(): RD {
        return ReadProductDto(
            id = this.id,
            name = this.name,
            price = this.price,
            brand = this.brand.toReadDto(),
            createdAt = this.createdAt,
            modifiedAt = this.modifiedAt,
            deletedAt = this.deletedAt,
        ) as RD
    }
}
