package com.example.jwt.user.application

import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.user.application.exception.DuplicatedEmailException
import com.example.jwt.user.application.exception.NotFoundUserException
import com.example.jwt.user.application.exception.NotMatchPasswordException
import com.example.jwt.user.domain.Authority
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
    fun findByEmail(email: String): UserDto {
        val user = userRepository.findByEmail(email).orElseThrow { NotFoundUserException() }
        return UserDto.of(user)
    }

    fun findByEmailAndPassword(email: String, password: String): UserDto {
        val user = userRepository.findByEmail(email).orElseThrow { NotFoundUserException() }
        if (!passwordEncoder.matches(password, user.password)) throw NotMatchPasswordException()
        return UserDto.of(user)
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