package com.example.jwt._common.dto

class SingleResult<T>(
    statusCode: Int,
    message: String?,
    val data: T,
) : EmptyResult(statusCode, message)