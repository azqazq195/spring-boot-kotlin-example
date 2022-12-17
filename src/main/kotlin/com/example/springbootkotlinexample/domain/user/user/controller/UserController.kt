package com.example.springbootkotlinexample.domain.user.user.controller

import com.example.springbootkotlinexample.common.base.response.ResponseDto
import com.example.springbootkotlinexample.domain.user.user.controller.dto.CreateUserDto
import com.example.springbootkotlinexample.domain.user.user.controller.dto.UpdateUserDto
import com.example.springbootkotlinexample.domain.user.user.entity.User
import com.example.springbootkotlinexample.domain.user.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping()
    fun findAll(): ResponseDto<List<User>> {
        val userList = userService.findAll()
        return ResponseDto(HttpStatus.OK, userList)
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): ResponseDto<User> {
        val user = userService.findById(id)
        return ResponseDto(HttpStatus.OK, user)
    }

    @PostMapping()
    fun create(@RequestBody createUserDto: CreateUserDto): ResponseDto<User> {
        val user = userService.create(createUserDto)
        return ResponseDto(HttpStatus.CREATED, user)
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Long, @RequestBody updateUserDto: UpdateUserDto): ResponseDto<User> {
        userService.update(id, updateUserDto)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }
}