package com.example.springbootkotlinexample.domain.example.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AuditingEntity
import com.example.springbootkotlinexample.domain.example.entity.constants.ExampleEnum
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity(name = "tb_example")
@EntityListeners(AuditingEntityListener::class)
data class Example(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val content: String,

    @Column(nullable = false)
    val count: Int,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val enum: ExampleEnum,

    @Embedded
    val audit: AuditingEntity = AuditingEntity()
)