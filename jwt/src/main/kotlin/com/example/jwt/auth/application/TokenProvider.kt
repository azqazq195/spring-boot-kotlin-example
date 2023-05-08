package com.example.jwt.auth.application

import com.example.jwt._common.util.RedisDao
import com.example.jwt._common.util.logger
import com.example.jwt.auth.application.exception.NotFoundRefreshTokenException
import com.example.jwt.auth.application.exception.NotMatchAccessToken
import com.example.jwt.auth.domain.Token
import com.example.jwt.auth.domain.TokenRepository
import com.example.jwt.auth.dto.RefreshTokenRequest
import com.example.jwt.auth.dto.TokenDto
import com.example.jwt.user.application.UserService
import com.example.jwt.user.dto.UserDto
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.Key
import java.time.Duration
import java.util.*
import java.util.stream.Collectors


@Service
class TokenProvider(
    @Value("\${jwt.secret}")
    private val accessSecret: String,
    @Value("\${jwt.access-token-expire-time}")
    private val accessTokenExpireTime: Long,
    @Value("\${jwt.refresh-secret}")
    private val refreshSecret: String,
    @Value("\${jwt.refresh-token-expire-time}")
    private val refreshTokenExpireTime: Long,
    private val redisDao: RedisDao,
    private val userService: UserService,
    private val tokenRepository: TokenRepository,
) {

    companion object {
        const val AUTHORITIES = "roles"
    }

    private val log = logger()
    private lateinit var accessKey: Key
    private lateinit var refreshKey: Key

    @PostConstruct
    protected fun init() {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret))
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret))
    }

    @Transactional
    fun create(user: UserDto): TokenDto {
        val token = Token(
            email = user.email,
            accessToken = createAccessToken(user),
            refreshToken = createRefreshToken(user),
            expiredAt = Date(System.currentTimeMillis() + refreshTokenExpireTime)
        )
        tokenRepository.save(token)
        return TokenDto.of(token)
    }

    @Transactional
    fun deleteByAccessToken(accessToken: String) {
        setBlackList(accessToken)
        tokenRepository.deleteByAccessToken(accessToken)
    }

    @Transactional
    fun refresh(refreshTokenRequest: RefreshTokenRequest): TokenDto {
        val token = tokenRepository.findByRefreshToken(refreshTokenRequest.refreshToken!!)
            .orElseThrow { NotFoundRefreshTokenException() }

        if (token.accessToken != refreshTokenRequest.accessToken)
            throw NotMatchAccessToken()

        deleteByAccessToken(token.accessToken)
        val user = userService.findByEmail(token.email)
        return create(user)
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)
        val email = claims.subject

        val authorities: Collection<GrantedAuthority> =
            Arrays.stream(claims[AUTHORITIES].toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray())
                .map { role: String -> SimpleGrantedAuthority(role.replace("[", "").replace("]", "")) }
                .collect(Collectors.toList())

        return UsernamePasswordAuthenticationToken(email, token, authorities)
    }

    fun validateToken(token: String): Boolean {
        if (redisDao.hasKeyInBlackList(token)) return false

        try {
            getClaims(token)
            return true
        } catch (e: SignatureException) {
            log.error("Invalid JWT signature: {}", e.message);
        } catch (e: MalformedJwtException) {
            log.error("Invalid JWT token: {}", e.message);
        } catch (e: ExpiredJwtException) {
            log.error("JWT token is expired: {}", e.message);
        } catch (e: UnsupportedJwtException) {
            log.error("JWT token is unsupported: {}", e.message);
        } catch (e: IllegalArgumentException) {
            log.error("JWT claims string is empty: {}", e.message);
        } catch (e: Exception) {
            log.error("JWT token is invalid: {}", e.message);
        }

        return false
    }

    private fun createAccessToken(user: UserDto): String {
        val now = Date()
        val expiration = Date(now.time + accessTokenExpireTime)

        return Jwts.builder()
            .setSubject(user.email)
            .claim(AUTHORITIES, user.authorities)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(accessKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun createRefreshToken(user: UserDto): String {
        val now = Date()
        val expiration = Date(now.time + refreshTokenExpireTime)

        return Jwts.builder()
            .setSubject(user.email)
            .claim(AUTHORITIES, user.authorities)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(refreshKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun setBlackList(accessToken: String) {
        val claims = getClaims(accessToken)
        val expiration = claims.expiration
        redisDao.setBlackList(accessToken, "access", Duration.ofMillis(expiration.time))
    }

    private fun getClaims(accessToken: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(accessKey)
            .build()
            .parseClaimsJws(accessToken)
            .body
    }
}