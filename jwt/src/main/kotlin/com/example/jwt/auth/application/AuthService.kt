package com.example.jwt.auth.application

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.dto.SingleResult
import com.example.jwt._common.util.logger
import com.example.jwt.auth.dto.SignInRequest
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.auth.dto.TokenRequest
import com.example.jwt.auth.dto.TokenResponse
import com.example.jwt.user.application.UserDetailsServiceImpl
import com.example.jwt.user.application.UserService
import com.example.jwt.user.domain.User
import com.example.jwt.user.dto.UserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun signIn(signInRequest: SignInRequest): ResponseEntity<SingleResult<TokenResponse>> {
        val user = userDetailsServiceImpl.loadUserByUsername(signInRequest.email!!).user
            .apply { if (!passwordEncoder.matches(signInRequest.password!!, password)) throw PasswordMatchException() }

        return ResponseDto.of(
            message = "로그인 완료.",
            status = HttpStatus.OK,
            data = jwtService.createToken(user)
        )
    }

    fun signOut(auth: Authentication): ResponseEntity<EmptyResult> {
        jwtService.signOut(auth)
        return ResponseDto.of(
            message = "로그아웃 완료.",
            status = HttpStatus.OK,
        )
    }

    fun signUp(signUpRequest: SignUpRequest): ResponseEntity<EmptyResult> {
        return userService.create(signUpRequest)
    }

    fun refresh(tokenRequest: TokenRequest): ResponseEntity<SingleResult<TokenResponse>> {
        logger().info("tokenRequest: $tokenRequest")
        return ResponseDto.of(
            message = "토큰 갱신 완료.",
            status = HttpStatus.OK,
            data = jwtService.refresh(tokenRequest)
        )
    }

    fun me(currentUser: User): ResponseEntity<SingleResult<UserDto>> {
        return userService.select(currentUser.id!!)
    }
}