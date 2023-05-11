package com.example.jwt.support

import com.example.jwt.auth.domain.Token
import java.util.*

object TokenFixture {
    fun create(): Token {
        return Token(
            email = "user@example.com",
            accessToken = "accessToken",
            refreshToken = "refreshToken",
            expiredAt = Date()
        )
    }
}