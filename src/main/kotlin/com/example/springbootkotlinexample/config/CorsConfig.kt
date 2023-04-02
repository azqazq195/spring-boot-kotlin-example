package com.example.springbootkotlinexample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

//@Configuration
class CorsConfig {
//    @Bean
//    fun corsFilter(): CorsFilter {
//        val config = CorsConfiguration()
//        config.allowCredentials = true
//        //config.addAllowedOrigin("*"); // java.lang.IllegalArgumentException: When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on the "Access-Control-Allow-Origin" response header. To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
//        config.addAllowedOriginPattern("*") // e.g. http://domain1.com
//        config.addAllowedHeader("*")
//        config.addAllowedMethod("*")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", config)
//        return CorsFilter(source)
//    }
}