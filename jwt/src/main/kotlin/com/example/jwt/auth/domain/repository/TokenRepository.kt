package com.example.jwt.auth.domain.repository

import com.example.jwt.auth.domain.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, String> {
    fun findByRefreshToken(refreshToken: String): Optional<Token>
    fun deleteByAccessToken(accessToken: String)
}