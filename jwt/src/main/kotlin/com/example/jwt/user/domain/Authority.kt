package com.example.jwt.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority

@Entity
data class Authority(
    @Id @Column(length = 20, nullable = false, unique = true)
    val name: String
) : GrantedAuthority {
    override fun getAuthority(): String = name
}