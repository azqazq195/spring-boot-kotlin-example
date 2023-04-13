package com.example.jwt.user.application

import com.example.jwt._common.dto.EmptyResult
import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.dto.SingleResult
import com.example.jwt.auth.dto.SignUpRequest
import com.example.jwt.user.domain.UserRepository
import com.example.jwt.user.dto.UserDto
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun select(id: Long): ResponseEntity<SingleResult<UserDto>> {
        val user = userRepository.findById(id).orElseThrow()
        return ResponseDto.of(
            message = "사용자 조회 완료.",
            status = HttpStatus.OK,
            data = UserDto.of(user)
        )
    }

    @Transactional
    fun create(signUpRequest: SignUpRequest): ResponseEntity<EmptyResult> {
        if (userRepository.existsByEmail(signUpRequest.email!!))
            throw ExistsEmailException()

        userRepository.save(
            signUpRequest.toUser(
                passwordEncoder.encode(signUpRequest.password!!)
            )
        )

        return ResponseDto.of(
            message = "사용자 생성 완료.",
            status = HttpStatus.CREATED
        )
    }
}