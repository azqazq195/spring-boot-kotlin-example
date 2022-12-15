package com.example.springbootkotlinexample.domain.product.entity

import com.example.springbootkotlinexample.common.generic.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.Comment

@Entity
data class Product(
    override val id: Long? = null,

    @field:Column(
        length = 255,
        nullable = false,
    )
    @field:Comment("이름")
    val name: String,
): BaseEntity(id) {
    fun changeName(name: String) = copy(name = name)
}