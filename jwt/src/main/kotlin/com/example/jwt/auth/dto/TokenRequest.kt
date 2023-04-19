package com.example.jwt.auth.dto

import jakarta.validation.constraints.NotBlank

data class TokenRequest(
    @field:NotBlank
    val accessToken: String?,
    @field:NotBlank
    val refreshToken: String?
)