package com.example.jwt.auth.application

import com.example.jwt.auth.dto.RefreshTokenRequest
import com.example.jwt.auth.dto.SignInRequest
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.auth.dto.TokenDto
import com.example.jwt.user.application.UserService
import com.example.jwt.user.dto.UserDto
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenProvider: TokenProvider,
    private val userService: UserService,
) {
    fun signIn(signInRequest: SignInRequest): TokenDto {
        val user = userService.findByEmailAndPassword(signInRequest.email!!, signInRequest.password!!)
        return tokenProvider.create(user)
    }

    fun signOut(auth: Authentication) {
        val accessToken = auth.credentials as String
        tokenProvider.deleteByAccessToken(accessToken)
    }

    fun signUp(signUpRequest: SignUpRequest) {
        userService.create(signUpRequest)
    }

    fun refresh(refreshTokenRequest: RefreshTokenRequest): TokenDto {
        return tokenProvider.refresh(refreshTokenRequest)
    }

    fun me(auth: Authentication): UserDto {
        val email = auth.principal as String
        return userService.findByEmail(email)
    }
}