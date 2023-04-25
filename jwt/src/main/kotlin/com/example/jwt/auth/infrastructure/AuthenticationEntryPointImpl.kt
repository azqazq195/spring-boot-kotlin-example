package com.example.jwt.auth.infrastructure

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.exception.ErrorCode
import com.example.jwt._common.util.LocalDateTimeSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AuthenticationEntryPointImpl(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
        objectMapper.registerModule(simpleModule)

        response.status = ErrorCode.INVALID_TOKEN.status.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(
            objectMapper.writeValueAsString(
                EmptyResult(
                    statusCode = ErrorCode.INVALID_TOKEN.status.value(),
                    message = ErrorCode.INVALID_TOKEN.message,
                )
            )
        )
        response.writer.flush()
    }
}