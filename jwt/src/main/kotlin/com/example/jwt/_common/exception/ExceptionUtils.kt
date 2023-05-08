package com.example.jwt._common.exception

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.util.LocalDateTimeSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import java.time.LocalDateTime

object ExceptionUtils {
    private val objectMapper = ObjectMapper()
    
    fun handleException(
        response: HttpServletResponse,
        errorCode: ErrorCode
    ) {
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
        objectMapper.registerModule(simpleModule)

        response.status = errorCode.status.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(
            objectMapper.writeValueAsString(
                EmptyResult(
                    statusCode = errorCode.status.value(),
                    message = errorCode.message,
                )
            )
        )
        response.writer.flush()
    }
}