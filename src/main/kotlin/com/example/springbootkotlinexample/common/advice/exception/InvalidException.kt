package com.example.springbootkotlinexample.common.advice.exception

open class InvalidException(argName: String) : RuntimeException("$argName 값이 유효하지 않습니다.")
