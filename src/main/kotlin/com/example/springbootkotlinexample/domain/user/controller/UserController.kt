package com.example.springbootkotlinexample.domain.user.controller

import com.example.springbootkotlinexample.common.base.controller.AbstractController
import com.example.springbootkotlinexample.domain.user.controller.dto.CreateUserDto
import com.example.springbootkotlinexample.domain.user.controller.dto.ReadUserDto
import com.example.springbootkotlinexample.domain.user.controller.dto.UpdateUserDto
import com.example.springbootkotlinexample.domain.user.entity.User
import com.example.springbootkotlinexample.domain.user.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) : AbstractController<User, CreateUserDto, UpdateUserDto, ReadUserDto>(
    userService,
)