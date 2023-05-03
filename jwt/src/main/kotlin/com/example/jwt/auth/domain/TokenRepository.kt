package com.example.jwt.auth.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, String> {
    fun findByRefreshToken(refreshToken: String): Optional<Token>
    fun deleteByAccessToken(accessToken: String)
}