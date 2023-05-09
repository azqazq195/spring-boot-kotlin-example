package com.example.jwt.user.domain.repository

import com.example.jwt._common.domain.repository.DeletableRepository
import com.example.jwt.user.domain.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : DeletableRepository<User> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<User>
}
