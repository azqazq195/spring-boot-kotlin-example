package com.example.springbootkotlinexample.domain.user.user.service.exception

import com.example.springbootkotlinexample.common.base.service.exception.NotFoundEntityException

class NotFoundUserException : NotFoundEntityException(entityName = "사용자")
