package com.example.jwt.user.application

import com.example.jwt.user.application.exception.NotFoundUserException
import com.example.jwt.user.domain.UserDetailsImpl
import com.example.jwt.user.domain.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(userPk: String): UserDetailsImpl {
        val user = userRepository.findByEmail(userPk).orElseThrow { NotFoundUserException() }
        return UserDetailsImpl(user)
    }
}