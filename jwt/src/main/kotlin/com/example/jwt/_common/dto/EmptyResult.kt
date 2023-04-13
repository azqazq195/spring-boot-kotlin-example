package com.example.jwt._common.dto

import java.time.LocalDateTime

open class EmptyResult(
    val statusCode: Int,
    val message: String? = null
) {
    val timestamp: LocalDateTime = LocalDateTime.now()
}