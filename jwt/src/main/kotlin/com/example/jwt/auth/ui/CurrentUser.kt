package com.example.jwt.auth.ui

import org.springframework.security.core.annotation.AuthenticationPrincipal

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
@AuthenticationPrincipal(expression = "#this.user")
annotation class CurrentUser