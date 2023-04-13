package com.example.jwt.user.ui

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt.user.application.ExistsEmailException
import com.example.jwt.user.application.NotFoundUserException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserAdvice {
    @ExceptionHandler(ExistsEmailException::class)
    fun existsEmailException(
        request: HttpServletRequest,
        e: ExistsEmailException
    ): ResponseEntity<EmptyResult> {
        return ResponseDto.of(
            HttpStatus.BAD_REQUEST,
            e.message
        )
    }

    @ExceptionHandler(NotFoundUserException::class)
    fun notFoundUserException(
        request: HttpServletRequest,
        e: NotFoundUserException
    ): ResponseEntity<EmptyResult> {
        return ResponseDto.of(
            HttpStatus.NOT_FOUND,
            e.message
        )
    }
}