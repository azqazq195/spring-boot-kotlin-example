package com.example.springbootkotlinexample.domain.user.controller.dto

import com.example.springbootkotlinexample.common.dto.UpdateDto
import com.example.springbootkotlinexample.domain.user.entity.User

data class UpdateUserDto(
    val username: String?,
    val password: String?,
    val name: String?,
    val age: Int?
) : UpdateDto<User>() {
    override fun toUpdatedEntity(entity: User): User = User(
        id = entity.id,
        username = username ?: entity.username,
        password = password ?: entity.password,
        name = name ?: entity.name,
        age = age ?: entity.age
    )
}