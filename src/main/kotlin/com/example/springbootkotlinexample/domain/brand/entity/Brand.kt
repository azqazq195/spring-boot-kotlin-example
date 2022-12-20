package com.example.springbootkotlinexample.domain.brand.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.entity.AbstractAuditingEntity
import com.example.springbootkotlinexample.domain.brand.controller.dto.UpdateBrandDto
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "tb_brand")
class Brand(
    name: String,
) : AbstractAuditingEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    override fun <UD : AbstractUpdateDto<E>, E> update(dto: UD) {
        val updateBrandDto = dto as UpdateBrandDto
        this.name = updateBrandDto.name ?: this.name
    }
}
