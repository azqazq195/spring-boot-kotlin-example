package com.example.jwt.user.domain

import com.example.jwt._common.domain.DeletableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "tb_user")
data class User(
    @Column(length = 20, nullable = false, unique = true)
    val email: String,

    @Column(length = 255, nullable = false)
    val password: String,

    @Column(length = 10, nullable = false)
    val name: String,

    @Column(nullable = true)
    val age: Int? = null
) : DeletableEntity()