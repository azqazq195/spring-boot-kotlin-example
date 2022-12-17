package com.example.springbootkotlinexample.domain.example.entity

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.entity.AbstractAuditingEntity
import com.example.springbootkotlinexample.common.base.entity.IEntity
import com.example.springbootkotlinexample.domain.example.controller.dto.UpdateExampleDto
import jakarta.persistence.Entity

@Entity(name = "tb_example")
class Example(
    name: String,
    content: String,
    count: Int,
) : AbstractAuditingEntity(), IEntity {
    var name: String = name
        private set

    var content: String = content
        private set

    var count: Int = count
        private set

    override fun <UD : IUpdateDto<E>, E> update(dto: UD) {
        val updateExampleDto = dto as UpdateExampleDto
        this.name = updateExampleDto.name ?: this.name
        this.content = updateExampleDto.content ?: this.content
        this.count = updateExampleDto.count ?: this.count
    }
}