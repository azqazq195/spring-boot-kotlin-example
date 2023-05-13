package com.example.jwt.support.ui

import com.example.jwt.support.config.TestConfiguration
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.context.annotation.Import
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.headers.HeaderDocumentation.*
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.restdocs.snippet.Snippet
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension::class)
@Import(TestConfiguration::class)
abstract class RestControllerTest {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp(
        webApplicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider
    ) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
            .apply<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(
                    restDocumentationContextProvider
                )
                    .operationPreprocessors()
                    .withRequestDefaults(Preprocessors.prettyPrint())
                    .withResponseDefaults(Preprocessors.prettyPrint())
            )
            .build()
    }

    fun MockHttpServletRequestDsl.bearer(token: String) {
        fun bearerToken(token: String): String = "Bearer $token"

        header(HttpHeaders.AUTHORIZATION, bearerToken(token))
    }


    fun MockHttpServletRequestDsl.jsonContent(value: Any) {
        content = objectMapper.writeValueAsString(value)
        contentType = MediaType.APPLICATION_JSON
    }

    fun MockMvcResultMatchersDsl.success(response: Any? = null) {
        jsonPath("statusCode") { exists() }
        jsonPath("message") { exists() }
        jsonPath("timestamp") { exists() }
//        response?.let {
//            val responseData = objectMapper.writeValueAsString(response)
//            val jsonNode = objectMapper.readTree(responseData)
//            val data = objectMapper.convertValue(it, object : TypeReference<Map<String, Any>>() {})
//            jsonPath("$.data") { objectMapper.writeValueAsString(response) }
//        }
    }

    fun MockMvcResultMatchersDsl.failed() {
        jsonPath("statusCode") { exists() }
        jsonPath("message") { exists() }
        jsonPath("timestamp") { exists() }
    }

    fun MockMvcResultHandlersDsl.document(
        identifier: String,
        queryParameters: List<ParameterDescriptor>? = null,
        requestFields: List<FieldDescriptor>? = null,
        responseFields: List<FieldDescriptor>? = null
    ) {
        val handlers = mutableListOf<Snippet>()

        queryParameters?.let {
            handlers.add(queryParameters(it))
        }

        requestFields?.let {
            handlers.add(requestFields(it))
        }

        responseFields?.let {
            handlers.add(
                responseFields(
                    beneathPath("data").withSubsectionId("data"),
                    it
                )
            )
        }

        handle(MockMvcRestDocumentation.document(identifier, *handlers.toTypedArray()))
    }
}