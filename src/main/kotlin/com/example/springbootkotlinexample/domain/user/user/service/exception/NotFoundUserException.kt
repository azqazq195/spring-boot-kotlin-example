package com.example.springbootkotlinexample.domain.user.user.service.exception

import com.example.springbootkotlinexample.common.advice.exception.NotFoundException

class NotFoundUserException : NotFoundException(entityName = "사용자")
