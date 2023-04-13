package com.example.jwt.user.domain

import com.example.jwt._common.domain.DeletableRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : DeletableRepository<User> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<User>
}
