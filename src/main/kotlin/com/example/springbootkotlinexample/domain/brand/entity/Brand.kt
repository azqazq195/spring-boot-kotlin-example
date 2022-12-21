package com.example.springbootkotlinexample.domain.brand.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractReadDto
import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.entity.AbstractAuditingEntity
import com.example.springbootkotlinexample.domain.brand.controller.dto.ReadBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.UpdateBrandDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany

@Entity(name = "tb_brand")
class Brand(
    name: String,
) : AbstractAuditingEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "brand")
    var products: MutableList<Product> = mutableListOf()
        private set

    fun addProduct(product: Product) {
        this.products.add(product)
//        product.setBrand(this)
    }

    override fun <RD: AbstractReadDto<E>, E> toReadDto(): RD {
        return ReadBrandDto(
            id = this.id,
            name = this.name,
            createdAt = this.createdAt,
            modifiedAt = this.modifiedAt,
            deletedAt = this.deletedAt,
        ) as RD
    }
}
