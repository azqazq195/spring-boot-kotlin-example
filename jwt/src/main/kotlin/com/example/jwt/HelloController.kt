package com.example.jwt

import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.dto.SingleResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {
    @GetMapping("/me")
    fun me(auth: Authentication): ResponseEntity<SingleResult> {
        return ResponseDto.of(
            message = "나를 조회",
            status = HttpStatus.OK,
            data = mapOf(
                "email" to auth.principal,
                "authorities" to auth.authorities,
            )
        )
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    fun user(auth: Authentication): ResponseEntity<SingleResult> {
        return ResponseDto.of(
            message = "유저 조회",
            status = HttpStatus.OK,
            data = mapOf(
                "email" to auth.principal,
                "authorities" to auth.authorities,
            )
        )
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun admin(auth: Authentication): ResponseEntity<SingleResult> {
        return ResponseDto.of(
            message = "관리자 조회",
            status = HttpStatus.OK,
            data = mapOf(
                "email" to auth.principal,
                "authorities" to auth.authorities,
            )
        )
    }
}