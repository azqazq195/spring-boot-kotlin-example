package com.example.springbootkotlinexample.domain.user.controller.dto

import com.example.springbootkotlinexample.common.dto.CreateDto
import com.example.springbootkotlinexample.domain.user.entity.User

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