package com.example.springbootkotlinexample.common.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseDto<T> : ResponseEntity<ResponseDto.Response<T>> {
    constructor(httpStatus: HttpStatus) : super(Response(httpStatus), httpStatus)
    constructor(httpStatus: HttpStatus, data: T) : super(Response(httpStatus, data), httpStatus)
    constructor(httpStatus: HttpStatus, message: String?) : super(Response(httpStatus, message), httpStatus)
    constructor(httpStatus: HttpStatus, message: String?, data: T) : super(
        Response(httpStatus, message ?: httpStatus.reasonPhrase, data),
        httpStatus
    )

    data class Response<T>(val status: Int, val message: String, val data: T?) {
        constructor(httpStatus: HttpStatus) : this(httpStatus.value(), httpStatus.reasonPhrase, null)
        constructor(httpStatus: HttpStatus, data: T?) : this(httpStatus.value(), httpStatus.reasonPhrase, data)
        constructor(httpStatus: HttpStatus, message: String?) : this(
            httpStatus.value(),
            message ?: httpStatus.reasonPhrase,
            null
        )

        constructor(httpStatus: HttpStatus, message: String?, data: T) : this(
            httpStatus.value(),
            message ?: httpStatus.reasonPhrase,
            data
        )
    }
}
