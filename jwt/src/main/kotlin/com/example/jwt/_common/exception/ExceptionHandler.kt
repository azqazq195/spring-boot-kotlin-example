package com.example.jwt._common.exception

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.util.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    private val log = logger()

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<EmptyResult> {
        // TODO print stack trace only in development
        e.printStackTrace()
        log.error("unhandled exception: {}", e.message)
        return ResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
    }

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<EmptyResult> {
        // TODO print stack trace only in development
        e.printStackTrace()
        return ResponseDto.of(e.errorCode)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<EmptyResult> {
        // TODO print stack trace only in development
        e.printStackTrace()
        val firstFieldError = e.bindingResult.allErrors[0] as FieldError
        val message = "[${firstFieldError.field}] ${firstFieldError.defaultMessage}"
        return ResponseDto.of(HttpStatus.BAD_REQUEST, message)
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException::class)
    fun handleAccessDeniedException(e: org.springframework.security.access.AccessDeniedException): ResponseEntity<EmptyResult> {
        // TODO print stack trace only in development
        e.printStackTrace()
        return ResponseDto.of(ErrorCode.FORBIDDEN)
    }

}