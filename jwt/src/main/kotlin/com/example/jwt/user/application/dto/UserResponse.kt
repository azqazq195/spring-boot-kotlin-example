package com.example.jwt.user.application.dto

import com.example.jwt.user.domain.User
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val email: String,
    val authorities: List<String>,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime
) {
    constructor(user: User) : this(
        id = user.id!!,
        email = user.email,
        authorities = user.authorities.map { it.name }.toList(),
        createdAt = user.createdAt!!,
        modifiedAt = user.modifiedAt!!
    )
}