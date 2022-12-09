package com.example.jwt.common.advice

import com.example.jwt.common.dto.ResponseDto
import com.example.jwt.common.exception.NotFoundException
import com.example.jwt.config.logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = logger()

    @ExceptionHandler(NotFoundException::class)
    fun handleApiException(e: NotFoundException): ResponseDto<Void> {
        log.warn("not found exception {}", e.message)
        return ResponseDto(HttpStatus.NOT_FOUND, e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception?): ResponseDto<Void> {
        log.error("unhandled exception {}", e?.message)
        return ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}