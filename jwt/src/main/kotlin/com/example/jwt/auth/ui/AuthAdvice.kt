package com.example.jwt.auth.ui

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt.auth.application.PasswordMatchException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthAdvice {
    @ExceptionHandler(PasswordMatchException::class)
    fun passwordMatchException(
        request: HttpServletRequest,
        e: PasswordMatchException
    ): ResponseEntity<EmptyResult> {
        return ResponseDto.of(
            HttpStatus.BAD_REQUEST,
            e.message
        )
    }
}