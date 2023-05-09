package com.example.jwt.auth.application

import com.example.jwt.auth.application.dto.RefreshTokenRequest
import com.example.jwt.auth.application.dto.SignInRequest
import com.example.jwt.auth.application.dto.SignUpRequest
import com.example.jwt.auth.application.dto.TokenResponse
import com.example.jwt.user.application.UserService
import com.example.jwt.user.application.dto.UserResponse
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenProvider: TokenProvider,
    private val userService: UserService,
) {
    fun signIn(signInRequest: SignInRequest): TokenResponse {
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

    fun refresh(refreshTokenRequest: RefreshTokenRequest): TokenResponse {
        return tokenProvider.refresh(refreshTokenRequest)
    }

    fun me(auth: Authentication): UserResponse {
        val email = auth.principal as String
        return userService.findByEmail(email)
    }
}