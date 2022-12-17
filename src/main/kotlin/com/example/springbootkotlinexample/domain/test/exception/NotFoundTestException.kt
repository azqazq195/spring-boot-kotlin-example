package com.example.springbootkotlinexample.domain.test.exception

import com.example.springbootkotlinexample.common.advice.exception.NotFoundException

class NotFoundTestException : NotFoundException(entityName = "테스트")
