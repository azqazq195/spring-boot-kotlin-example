package com.example.springbootkotlinexample.common.advice.exception

open class MethodNotAllowedException : RuntimeException("지원하지 않는 기능 입니다.")