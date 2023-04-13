package com.example.jwt.auth.ui

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.SingleResult
import com.example.jwt.auth.application.AuthService
import com.example.jwt.auth.dto.SignInRequest
import com.example.jwt.auth.dto.SignInResponse
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.user.domain.User
import com.example.jwt.user.domain.UserDetailsImpl
import com.example.jwt.user.dto.UserDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody @Valid signInRequest: SignInRequest
    ): ResponseEntity<SingleResult<SignInResponse>> {
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

    @GetMapping("/me")
    fun me(
        @CurrentUser user: User
    ): ResponseEntity<SingleResult<UserDto>> {
        return authService.me(user)
    }

    @GetMapping("/me2")
    fun me2(
        @AuthenticationPrincipal user: UserDetailsImpl
    ) {
        println(user)
    }
}