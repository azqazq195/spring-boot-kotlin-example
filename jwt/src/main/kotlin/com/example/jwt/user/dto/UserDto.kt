package com.example.jwt.user.dto

import com.example.jwt.user.domain.User
import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    val email: String,
    val authorities: List<String>,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun of(user: User) = UserDto(
            id = user.id!!,
            email = user.email,
            authorities = user.authorities.map { it.name }.toList(),
            createdAt = user.createdAt!!,
            modifiedAt = user.modifiedAt!!
        )
    }
}