package com.example.jwt.support

import com.example.jwt.user.domain.Role
import com.example.jwt.user.domain.User

fun createUser(): User {
    return User(
        email = "user@example.com",
        password = "password",
        name = "사용자",
        age = 30,
        role = Role.USER,
    )
}