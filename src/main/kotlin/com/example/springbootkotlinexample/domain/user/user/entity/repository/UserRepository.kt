package com.example.springbootkotlinexample.domain.user.user.entity.repository

import com.example.springbootkotlinexample.domain.user.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}