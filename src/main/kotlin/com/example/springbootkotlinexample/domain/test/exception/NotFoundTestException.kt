package com.example.springbootkotlinexample.domain.test.exception

import com.example.springbootkotlinexample.common.exception.NotFoundException

class NotFoundTestException : NotFoundException(entityName = "테스트")
