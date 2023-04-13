package com.example.jwt.auth.application

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.dto.SingleResult
import com.example.jwt.auth.JwtStorage
import com.example.jwt.auth.dto.SignInRequest
import com.example.jwt.auth.dto.SignInResponse
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.auth.ui.CurrentUser
import com.example.jwt.user.application.UserDetailsServiceImpl
import com.example.jwt.user.application.UserService
import com.example.jwt.user.domain.User
import com.example.jwt.user.dto.UserDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class AuthService(
    private val userDetailsServiceImpl: UserDetailsServiceImpl,
    private val userService: UserService,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder
) {
    fun signIn(
        @RequestBody @Valid signInRequest: SignInRequest
    ): ResponseEntity<SingleResult<SignInResponse>> {
        val user = userDetailsServiceImpl.loadUserByUsername(signInRequest.email!!).user
            .apply { if (!passwordEncoder.matches(signInRequest.password!!, password)) throw PasswordMatchException() }

        val accessToken = jwtProvider.createToken(user.email)
        return ResponseDto.of(
            message = "로그인 완료.",
            status = HttpStatus.OK,
            data = SignInResponse(accessToken = accessToken)
        )
    }

    fun signOut(
        auth: Authentication
    ): ResponseEntity<EmptyResult> {
        JwtStorage.signOut(auth.credentials.toString())
        return ResponseDto.of(
            message = "로그아웃 완료.",
            status = HttpStatus.OK,
        )
    }

    fun signUp(
        @RequestBody @Valid signUpRequest: SignUpRequest
    ): ResponseEntity<EmptyResult> {
        return userService.create(signUpRequest)
    }

    fun me(
        @CurrentUser currentUser: User
    ): ResponseEntity<SingleResult<UserDto>> {
        return userService.select(currentUser.id!!)
    }
}