package com.example.jwt._common.ui

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.util.logger
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class CommonAdvice {

    private val log = logger()

    @ExceptionHandler(RuntimeException::class)
    fun handleException(e: Exception): ResponseEntity<EmptyResult> {
        log.error("unhandled exception: {}", e.message)
        return ResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<EmptyResult> {
        val firstFieldError = e.bindingResult.allErrors[0] as FieldError
        val message = "[${firstFieldError.field}] ${firstFieldError.defaultMessage}"
        return ResponseDto.of(HttpStatus.BAD_REQUEST, message)
    }

}