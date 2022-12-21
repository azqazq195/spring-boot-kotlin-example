package com.example.springbootkotlinexample.domain.user.user.service

import com.example.springbootkotlinexample.domain.user.user.controller.dto.CreateUserDto
import com.example.springbootkotlinexample.domain.user.user.controller.dto.UpdateUserDto
import com.example.springbootkotlinexample.domain.user.user.entity.User
import com.example.springbootkotlinexample.domain.user.user.entity.repository.UserRepository
import com.example.springbootkotlinexample.domain.user.user.service.exception.NotFoundUserException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username).orElseThrow { RuntimeException() }
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
//        val user = updateUserDto.toEntity(target)
        return userRepository.save(target);
    }
}


