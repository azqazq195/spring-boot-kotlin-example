package com.example.springbootkotlinexample.config

import com.example.springbootkotlinexample.common.interceptor.LoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val loggingInterceptor: LoggingInterceptor
) : WebMvcConfigurer {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(loggingInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/auth/sign-in")
        super.addInterceptors(registry)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin().disable()
            .rememberMe().disable()
            .logout().disable()
            .headers().disable()
            // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
            .httpBasic().disable()
            // csrf 보안 토큰 disable 처리.
            .csrf().disable()
            // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/**").permitAll()
//            .requestMatchers("/h2-console/**").permitAll()
//            .requestMatchers("/user/**").permitAll()
//            .requestMatchers("/user/**").hasAuthority("ROLE_USER")
        return http.build()
    }
}