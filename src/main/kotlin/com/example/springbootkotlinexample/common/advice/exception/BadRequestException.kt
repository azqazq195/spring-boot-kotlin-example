package com.example.springbootkotlinexample.common.advice.exception

open class BadRequestException(message: String? = null) : RuntimeException(message ?: "잘못된 요청입니다.")