package com.example.springbootkotlinexample.domain.brand.entity

import com.example.springbootkotlinexample.common.base.entity.AuditingEntity
import com.example.springbootkotlinexample.domain.product.entity.Product
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity(name = "tb_brand")
@EntityListeners(AuditingEntityListener::class)
data class Brand(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "brand")
    val products: MutableList<Product> = mutableListOf(),

    @Embedded
    val audit: AuditingEntity = AuditingEntity(),
) {
    fun addProduct(product: Product) {
        this.products.add(product)
    }
}
