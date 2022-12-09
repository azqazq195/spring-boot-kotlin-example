package com.example.springbootkotlinexample.common.interceptor

import com.example.springbootkotlinexample.config.logger
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class LoggingInterceptor(
    private val objectMapper: ObjectMapper
) : HandlerInterceptor {
    private val log = logger()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.info(
            "-- [REQUEST] [{}] {}, param: '{}'",
            request.method,
            request.requestURI,
            request.queryString
        )
        return super.preHandle(request, response, handler)
    }

//    override fun afterCompletion(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        handler: Any,
//        ex: Exception?
//    ) {
//        val cachingRequest: ContentCachingRequestWrapper = request as ContentCachingRequestWrapper
//        val cachingResponse: ContentCachingResponseWrapper = response as ContentCachingResponseWrapper
//        val requestBody: String =
//            java.lang.String.valueOf(objectMapper.readTree(cachingRequest.contentAsByteArray))
//        val responseBody: String =
//            java.lang.String.valueOf(objectMapper.readTree(cachingResponse.contentAsByteArray))
//        log.info(
//            "-- [RESPONSE] [{}] {}, param: '{}'",
//            request.method,
//            request.requestURI,
//            request.queryString
//        )
//        if (ex == null) {
//            log.info("-- request body: {}", requestBody)
//            log.info("-- response body: {}", responseBody)
//        } else {
//            log.info("-- exception")
//        }
//        log.info("-- [END]")
//        super.afterCompletion(request, response, handler, ex)
//    }
}