package com.example.jwt.domain.test.exception

import com.example.jwt.common.exception.NotFoundException

class NotFoundTestException : NotFoundException(entityName = "테스트")
