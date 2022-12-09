package com.example.jwt.domain.test.entity

import jakarta.persistence.*
import jakarta.persistence.Entity

@Entity(name = "tb_test")
data class Test(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String?,
    val comment: String,
    val count: Int,
)
