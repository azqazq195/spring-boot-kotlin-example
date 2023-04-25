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

        fun of(
            status: HttpStatus,
            message: String? = null,
            data: Any
        ): ResponseEntity<SingleResult> =
            ResponseEntity(
                SingleResult(
                    statusCode = status.value(),
                    message = message,
                    data = data
                ), status
            )

        fun of(
            status: HttpStatus,
            message: String? = null,
            data: List<Any>
        ): ResponseEntity<ListResult> =
            ResponseEntity(
                ListResult(
                    statusCode = status.value(),
                    message = message,
                    data = data
                ), status
            )
    }
}