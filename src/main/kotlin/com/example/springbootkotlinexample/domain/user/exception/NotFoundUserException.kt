package com.example.springbootkotlinexample.domain.user.exception

import com.example.springbootkotlinexample.common.exception.NotFoundException

class NotFoundUserException : NotFoundException(entityName = "사용자")
