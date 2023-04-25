package com.example.jwt.auth.application

import com.example.jwt.auth.dto.SignInRequest
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.auth.dto.TokenRequest
import com.example.jwt.auth.dto.TokenResponse
import com.example.jwt.user.application.UserDetailsServiceImpl
import com.example.jwt.user.application.UserService
import com.example.jwt.user.domain.User
import com.example.jwt.user.dto.UserDto
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userDetailsServiceImpl: UserDetailsServiceImpl,
    private val userService: UserService,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {
    fun signIn(signInRequest: SignInRequest): TokenResponse {
        val user = userDetailsServiceImpl.loadUserByUsername(signInRequest.email!!).user
            .apply { if (!passwordEncoder.matches(signInRequest.password!!, password)) throw PasswordMatchException() }

        return jwtService.createToken(user)
    }

    fun signOut(auth: Authentication) {
        jwtService.signOut(auth)
    }

    fun signUp(signUpRequest: SignUpRequest) {
        userService.create(signUpRequest)
    }

    fun refresh(tokenRequest: TokenRequest): TokenResponse {
        return jwtService.refresh(tokenRequest)
    }

    fun me(currentUser: User): UserDto {
        return userService.select(currentUser.id!!)
    }
}