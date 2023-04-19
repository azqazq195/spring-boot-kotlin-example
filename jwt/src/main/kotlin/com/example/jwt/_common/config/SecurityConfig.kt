package com.example.jwt._common.config

import com.example.jwt.auth.application.JwtService
import com.example.jwt.auth.infrastructure.AuthenticationEntryPointImpl
import com.example.jwt.auth.infrastructure.JwtAuthenticationFilter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationEntryPointImpl: AuthenticationEntryPointImpl,
    private val jwtService: JwtService,
    private val objectMapper: ObjectMapper
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .rememberMe().disable()
            .logout().disable()
            .httpBasic().disable()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeHttpRequests()
            .requestMatchers("/auth/sign-in").permitAll()
            .requestMatchers("/auth/sign-up").permitAll()
            .requestMatchers("/auth/refresh").permitAll()
            .anyRequest().authenticated()

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPointImpl) // 401 에러 핸들링
//            .accessDeniedHandler(authenticationEntryPointImpl) // 403 에러 핸들링

            .and()
            .addFilterBefore(
                JwtAuthenticationFilter(jwtService, objectMapper),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(AntPathRequestMatcher("/h2-console/**"))
            web.ignoring().requestMatchers(AntPathRequestMatcher("/docs/**"))
        }
    }
}