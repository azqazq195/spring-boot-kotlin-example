package com.example.jwt.domain.user.exception

import com.example.jwt.common.exception.NotFoundException

class NotFoundUserException : NotFoundException(entityName = "사용자")
