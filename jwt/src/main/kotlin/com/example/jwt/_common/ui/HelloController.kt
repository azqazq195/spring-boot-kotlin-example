package com.example.jwt._common.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {
    @GetMapping("/health")
    fun healthCheck(): ResponseEntity<Void> {
        return ResponseEntity.noContent().build()
    }
}

