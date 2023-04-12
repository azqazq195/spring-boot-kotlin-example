package com.example.jwt.user.application

import com.example.jwt._common.dto.ResponseDto
import com.example.jwt._common.dto.SingleResult
import com.example.jwt.user.domain.UserRepository
import com.example.jwt.user.dto.UserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun select(id: Long): ResponseEntity<SingleResult<UserDto>> {
        val user = userRepository.findById(id).orElseThrow()
        return ResponseDto.of(
            message = "사용자 조회",
            status = HttpStatus.OK,
            data = UserDto.of(user)
        )
    }
}