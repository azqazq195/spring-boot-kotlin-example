package com.example.jwt.auth.dto

import com.example.jwt.auth.domain.Token

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(token: Token) = TokenDto(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken
        )
    }
}