package com.example.jwt._common.dto

class ListResult<T>(
    statusCode: Int,
    message: String?,
    val data: List<T>,
) : EmptyResult(statusCode, message)