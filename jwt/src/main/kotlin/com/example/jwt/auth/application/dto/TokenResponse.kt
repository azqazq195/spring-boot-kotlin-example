package com.example.jwt.auth.application.dto

import com.example.jwt.auth.domain.Token

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(token: Token) = TokenResponse(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken
        )
    }
}