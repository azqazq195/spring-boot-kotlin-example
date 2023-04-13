package com.example.jwt._common.ui

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonAdvice {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<EmptyResult> {
        val firstFieldError = e.bindingResult.allErrors[0] as FieldError
        val message = "[${firstFieldError.field}] ${firstFieldError.defaultMessage}"
        return ResponseDto.of(HttpStatus.BAD_REQUEST, message)
    }
}