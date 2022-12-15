package com.example.springbootkotlinexample.domain.brand.entity

import com.example.springbootkotlinexample.common.generic.entity.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
data class Brand(
    override val id: Long? = null,

    @field:Column(
        length = 255,
        nullable = false,
    )
    @field:Comment("이름")
    val name: String,

    @field:Column(
        length = 255,
        nullable = false,
    )
    @Comment("설명")
    val description: String,

    @field:Column(
        length = 11,
        nullable = false,
    )
    @field:Comment("오픈 횟수")
    val openCount: Int = 0,
) : BaseEntity(id) {
    fun addOpenCount() = copy(openCount = openCount + 1)
}