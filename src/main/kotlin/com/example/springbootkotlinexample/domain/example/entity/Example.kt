package com.example.springbootkotlinexample.domain.example.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.entity.AbstractAuditingEntity
import com.example.springbootkotlinexample.domain.example.controller.dto.UpdateExampleDto
import com.example.springbootkotlinexample.domain.example.entity.constants.ExampleEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity(name = "tb_example")
class Example(
    name: String,
    content: String,
    count: Int,
    enum: ExampleEnum,
) : AbstractAuditingEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    @Column(nullable = false)
    var content: String = content
        private set

    @Column(nullable = false)
    var count: Int = count
        private set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var enum: ExampleEnum = enum
        private set

    override fun <UD : AbstractUpdateDto<E>, E> update(dto: UD) {
        val updateExampleDto = dto as UpdateExampleDto
        this.name = updateExampleDto.name ?: this.name
        this.content = updateExampleDto.content ?: this.content
        this.count = updateExampleDto.count ?: this.count
    }
}