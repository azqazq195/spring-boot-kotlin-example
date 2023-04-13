package com.example.jwt.auth.dto

import com.example.jwt.user.domain.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class SignUpRequest(
    @field:Email
    val email: String?,
    @field:NotBlank
    val password: String?,
    @field:NotBlank
    val name: String?,
    @field:Positive
    val age: Int?
) {
    fun toUser(encodedPassword: String) =
        User(
            email = email!!,
            password = encodedPassword,
            name = name!!,
            age = age!!
        )
}