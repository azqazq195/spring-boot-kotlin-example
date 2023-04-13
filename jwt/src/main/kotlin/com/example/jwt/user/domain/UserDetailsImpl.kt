package com.example.jwt.user.domain

data class UserDetailsImpl(
    val user: User
) : org.springframework.security.core.userdetails.User(
    user.email,
    user.password,
    emptyList()
)