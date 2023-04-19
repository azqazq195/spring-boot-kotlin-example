package com.example.jwt.auth.ui

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.SingleResult
import com.example.jwt._common.util.logger
import com.example.jwt.auth.application.AuthService
import com.example.jwt.auth.dto.SignInRequest
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.auth.dto.TokenRequest
import com.example.jwt.auth.dto.TokenResponse
import com.example.jwt.user.domain.User
import com.example.jwt.user.dto.UserDto
import jakarta.validation.Valid
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
    ): ResponseEntity<SingleResult<TokenResponse>> {
        return authService.signIn(signInRequest)
    }

    @GetMapping("/sign-out")
    fun signOut(auth: Authentication): ResponseEntity<EmptyResult> {
        return authService.signOut(auth)
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody @Valid signUpRequest: SignUpRequest
    ): ResponseEntity<EmptyResult> {
        return authService.signUp(signUpRequest)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody @Valid tokenRequest: TokenRequest
    ): ResponseEntity<SingleResult<TokenResponse>> {
        logger().info("tokenRequest: $tokenRequest")
        return authService.refresh(tokenRequest)
    }

    @GetMapping("/me")
    fun me(
        @CurrentUser user: User
    ): ResponseEntity<SingleResult<UserDto>> {
        return authService.me(user)
    }
}