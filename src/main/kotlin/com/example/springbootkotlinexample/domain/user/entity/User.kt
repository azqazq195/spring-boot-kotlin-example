package com.example.springbootkotlinexample.domain.user.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity(name = "tb_user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    private val username: String,
    private val password: String,
    val name: String,
    val age: Int?,
) : UserDetails {
    fun changeUsername(username: String): User = this.copy(username = username)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()
    override fun getUsername(): String = username

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}