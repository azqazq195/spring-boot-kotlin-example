package com.example.springbootkotlinexample.domain.product.entity

import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.common.base.entity.AuditingEntity
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity(name = "tb_product")
@EntityListeners(AuditingEntityListener::class)
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand,

    @Embedded
    val audit: AuditingEntity = AuditingEntity(),
) {
    fun changeBrand(brand: Brand) = this.copy(brand = brand)
}