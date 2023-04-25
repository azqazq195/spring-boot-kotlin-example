package com.example.jwt.auth.ui

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.dto.SingleResult
import com.example.jwt.auth.application.AuthService
import com.example.jwt.auth.dto.SignInRequest
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.auth.dto.TokenRequest
import com.example.jwt.user.domain.User
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody @Valid signInRequest: SignInRequest
    ): ResponseEntity<SingleResult> {
        val tokenResponse = authService.signIn(signInRequest)

        return ResponseDto.of(
            message = "로그인 완료.",
            status = HttpStatus.OK,
            data = tokenResponse
        )
    }

    @GetMapping("/sign-out")
    fun signOut(auth: Authentication): ResponseEntity<EmptyResult> {
        authService.signOut(auth)

        return ResponseDto.of(
            message = "로그아웃 완료.",
            status = HttpStatus.OK,
        )
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody @Valid signUpRequest: SignUpRequest
    ): ResponseEntity<EmptyResult> {
        authService.signUp(signUpRequest)

        return ResponseDto.of(
            message = "사용자 생성 완료.",
            status = HttpStatus.CREATED
        )
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody @Valid tokenRequest: TokenRequest
    ): ResponseEntity<SingleResult> {
        val tokenResponse = authService.refresh(tokenRequest)

        return ResponseDto.of(
            message = "토큰 갱신 완료.",
            status = HttpStatus.OK,
            data = tokenResponse
        )
    }

    @GetMapping("/me")
    fun me(
        @CurrentUser currentUser: User
    ): ResponseEntity<SingleResult> {
        val user = authService.me(currentUser)

        return ResponseDto.of(
            message = "사용자 조회 완료.",
            status = HttpStatus.OK,
            data = user
        )
    }
}