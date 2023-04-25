package com.example.jwt.auth.infrastructure

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.exception.ErrorCode
import com.example.jwt._common.util.LocalDateTimeSerializer
import com.example.jwt._common.util.logger
import com.example.jwt.auth.application.JwtService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.time.LocalDateTime

class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val objectMapper: ObjectMapper
) : GenericFilterBean() {

    private val log = logger()

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val token = jwtService.resolveToken((request as HttpServletRequest))

        // 토큰 확인
        if (token == null) {
            log.info("token is null")
            filterChain.doFilter(request, response)
            return
        }

        // 유효성 검사
        if (!jwtService.validateToken(token)) {
            log.info("token is not valid")
            filterChain.doFilter(request, response)
            return
        }

        try {
            val auth: Authentication = jwtService.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = auth
            log.info("token is valid")
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            writeResponse(
                response as HttpServletResponse,
                ErrorCode.INVALID_TOKEN
            )
        }
    }

    private fun writeResponse(response: HttpServletResponse, errorCode: ErrorCode) {
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
        objectMapper.registerModule(simpleModule)

        response.status = errorCode.status.value()
        response.contentType = "application/json"
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