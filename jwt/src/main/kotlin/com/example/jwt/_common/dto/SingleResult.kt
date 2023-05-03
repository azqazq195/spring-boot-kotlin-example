package com.example.jwt._common.dto

class SingleResult(
    statusCode: Int,
    message: String?,
    val data: Any,
) : EmptyResult(statusCode, message)