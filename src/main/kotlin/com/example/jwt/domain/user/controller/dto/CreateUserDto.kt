package com.example.jwt.domain.user.controller.dto

import com.example.jwt.common.dto.CreateDto
import com.example.jwt.domain.user.entity.User

data class CreateUserDto(
    val username: String,
    val password: String,
    val name: String,
    val age: Int?,
) : CreateDto<User>() {
    override fun toEntity(): User {
        return User(
            username = username,
            password = password,
            name = name,
            age = age
        )
    }
}