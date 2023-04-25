package com.example.jwt.user.application

import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.user.domain.UserRepository
import com.example.jwt.user.dto.UserDto
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun select(id: Long): UserDto {
        val user = userRepository.findById(id).orElseThrow()
        
        return UserDto.of(user)
    }

    @Transactional
    fun create(signUpRequest: SignUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email!!))
            throw ExistsEmailException()

        userRepository.save(
            signUpRequest.toUser(
                passwordEncoder.encode(signUpRequest.password!!)
            )
        )
    }
}