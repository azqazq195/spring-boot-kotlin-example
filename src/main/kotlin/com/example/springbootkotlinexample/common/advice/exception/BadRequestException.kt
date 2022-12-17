package com.example.springbootkotlinexample.common.advice.exception

open class BadRequestException : RuntimeException("잘못된 요청입니다.")