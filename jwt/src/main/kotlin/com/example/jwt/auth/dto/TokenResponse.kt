package com.example.jwt.auth.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)