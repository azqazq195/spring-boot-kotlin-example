package com.example.jwt._common.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseDto {
    companion object {
        fun of(
            status: HttpStatus,
            message: String? = null
        ): ResponseEntity<EmptyResult> =
            ResponseEntity(
                EmptyResult(
                    statusCode = status.value(),
                    message = message,
                ), status
            )

        fun <T> of(
            status: HttpStatus,
            message: String? = null,
            data: T
        ): ResponseEntity<SingleResult<T>> =
            ResponseEntity(
                SingleResult(
                    statusCode = status.value(),
                    message = message,
                    data = data
                ), status
            )

        fun <T> of(
            status: HttpStatus,
            message: String? = null,
            data: List<T>
        ): ResponseEntity<ListResult<T>> =
            ResponseEntity(
                ListResult(
                    statusCode = status.value(),
                    message = message,
                    data = data
                ), status
            )
    }
}