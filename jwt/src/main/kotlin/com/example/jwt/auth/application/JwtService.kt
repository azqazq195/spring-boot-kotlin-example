package com.example.jwt.auth.application

import com.example.jwt._common.util.RedisDao
import com.example.jwt.auth.dto.TokenRequest
import com.example.jwt.auth.dto.TokenResponse
import com.example.jwt.user.application.UserDetailsServiceImpl
import com.example.jwt.user.domain.User
import com.example.jwt.user.domain.UserDetailsImpl
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.access-token-expire-time}")
    private val accessTokenExpireTime: Long,
    @Value("\${jwt.refresh-token-expire-time}")
    private val refreshTokenExpireTime: Long,
    private val redisDao: RedisDao,
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) {
    companion object {
        const val AUTH_HEADER = "Authorization"
        const val ACCESS_TOKEN = "access"
        const val REFRESH_TOKEN = "refresh"
    }

    private lateinit var secretKey: String

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    // Jwt 토큰 생성
    fun createToken(user: User): TokenResponse {
        val accessToken = createToken(user, ACCESS_TOKEN, accessTokenExpireTime)
        val refreshToken = createToken(user, REFRESH_TOKEN, refreshTokenExpireTime)

        redisDao.set(user.email, refreshToken, refreshTokenExpireTime)

        return TokenResponse(
            accessToken,
            refreshToken
        )
    }

    private fun createToken(user: User, tokenType: String, expireTime: Long): String {
        val claims = Jwts.claims().setSubject(user.email)
        claims["roles"] = emptyList<String>()
        claims["type"] = tokenType
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + expireTime))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
            .compact()
    }

    fun signOut(auth: Authentication) {
        val userDetails = auth.principal as UserDetailsImpl
        val user = userDetails.user
        val token = auth.credentials as String
        val expiration = getExpiration(token)

        redisDao.delete(user.email)
        redisDao.setBlackList(token, ACCESS_TOKEN, expiration.time)
    }

    fun refresh(tokenRequest: TokenRequest): TokenResponse {
        val user = userDetailsServiceImpl.loadUserByUsername(getUserPk(tokenRequest.accessToken)).user
        val refreshToken = redisDao.get(user.email)
        if (refreshToken != tokenRequest.refreshToken) throw RuntimeException("RefreshToken is not valid")

        return createToken(user)
    }

    fun getAuthentication(token: String): Authentication {
        val principal = userDetailsServiceImpl.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(principal, token, principal.authorities)
    }

    fun getUserPk(token: String?): String {
        return Jwts
            .parserBuilder()
            .setSigningKey(Decoders.BASE64.decode(secretKey))
            .build()
            .parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        return req.getHeader(AUTH_HEADER)
    }

    fun validateToken(token: String): Boolean {
        if (redisDao.hasKeyBlackList(token)) return false

        return try {
            val claims =
                Jwts.parserBuilder()
                    .setSigningKey(Decoders.BASE64.decode(secretKey))
                    .build()
                    .parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            e.printStackTrace()
            // 만료 등 오류 처리 생략
            false
        }
    }

    fun getExpiration(token: String): Date {
        return Jwts
            .parserBuilder()
            .setSigningKey(Decoders.BASE64.decode(secretKey))
            .build()
            .parseClaimsJws(token).body.expiration
    }
}