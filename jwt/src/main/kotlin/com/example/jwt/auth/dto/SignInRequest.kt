package com.example.jwt.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignInRequest(
    @field:Email
    val email: String?,
    
    @field:NotBlank
    val password: String?,
)