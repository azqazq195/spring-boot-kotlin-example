package com.example.springbootkotlinexample.common.advice

import com.example.springbootkotlinexample.common.dto.ResponseDto
import com.example.springbootkotlinexample.common.exception.NotFoundException
import com.example.springbootkotlinexample.config.logger
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = logger()

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseDto<Void> {
        log.warn("http message not readable exception {}", e.message)
        return ResponseDto(HttpStatus.BAD_REQUEST, e.message)
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseDto<Void> {
        val firstFieldError = e.bindingResult.allErrors[0] as FieldError
        val message = "${firstFieldError.field}: ${firstFieldError.defaultMessage}"
        return ResponseDto(HttpStatus.BAD_REQUEST, message)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleApiException(e: NotFoundException): ResponseDto<Void> {
        log.warn("not found exception {}", e.message)
        return ResponseDto(HttpStatus.NOT_FOUND, e.message)
    }

    @ExceptionHandler(Exception::class)
    fun unHandleException(e: Exception?): ResponseDto<Void> {
        log.error("unhandled exception {}", e?.message)
        return ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}