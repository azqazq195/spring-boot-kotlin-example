package com.example.jwt.domain.user.service

import com.example.jwt.domain.user.controller.dto.CreateUserDto
import com.example.jwt.domain.user.controller.dto.UpdateUserDto
import com.example.jwt.domain.user.entity.User
import com.example.jwt.domain.user.repository.UserRepository
import com.example.jwt.domain.user.exception.NotFoundUserException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username).or리ElseThrow { RuntimeException() }
    }

    fun findAll(): List<User> {
        return userRepository.findAll();
    }

    fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow { NotFoundUserException() };
    }

    fun create(createUserDto: CreateUserDto): User {
        return userRepository.save(createUserDto.toEntity());
    }

    fun update(targetId: Long, updateUserDto: UpdateUserDto): User {
        val target = findById(targetId)
        val user = updateUserDto.toUpdatedEntity(target)
        return userRepository.save(user);
    }
}


