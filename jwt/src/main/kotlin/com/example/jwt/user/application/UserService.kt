package com.example.jwt.user.application

import com.example.jwt.auth.application.dto.SignUpRequest
import com.example.jwt.user.application.dto.UserResponse
import com.example.jwt.user.domain.Authority
import com.example.jwt.user.domain.repository.UserRepository
import com.example.jwt.user.exception.DuplicatedEmailException
import com.example.jwt.user.exception.NotFoundUserException
import com.example.jwt.user.exception.NotMatchPasswordException
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun findByEmail(email: String): UserResponse {
        val user = userRepository.findByEmail(email).orElseThrow { NotFoundUserException() }
        return UserResponse.of(user)
    }

    fun findByEmailAndPassword(email: String, password: String): UserResponse {
        val user = userRepository.findByEmail(email).orElseThrow { NotFoundUserException() }
        if (!passwordEncoder.matches(password, user.password)) throw NotMatchPasswordException()
        return UserResponse.of(user)
    }

    @Transactional
    fun create(signUpRequest: SignUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email!!))
            throw DuplicatedEmailException()

        userRepository.save(
            signUpRequest.toUser(
                passwordEncoder.encode(signUpRequest.password!!),
                setOf(Authority("ROLE_USER"))
            )
        )
    }
}