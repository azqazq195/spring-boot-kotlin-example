package com.example.springbootkotlinexample.common.exception

open class NotFoundException(entityName: String) : RuntimeException("존재하지 않는 ${entityName}입니다.")