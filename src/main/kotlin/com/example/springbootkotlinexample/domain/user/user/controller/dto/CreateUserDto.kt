package com.example.springbootkotlinexample.domain.user.user.controller.dto

import com.example.springbootkotlinexample.common.dto.CreateDto
import com.example.springbootkotlinexample.domain.user.user.entity.User
import jakarta.validation.constraints.NotBlank

data class CreateUserDto(
    val username: String,
    val password: String,
    val name: String,
    val nickName: String,
    @NotBlank
    val phone: String,
    val isCheckedPhone: Boolean,
    val isCheckedEmail: Boolean,
    val isReceivedPhone: Boolean,
    val isReceivedEmail: Boolean,
) : CreateDto<User>() {
    override fun toEntity(): User {
        return User(
            username = username,
            password = password,
            name = name,
            nickName = nickName,
            phone = phone,
            isCheckedPhone = isCheckedPhone,
            isCheckedEmail = isCheckedEmail,
            isReceivedPhone = isReceivedPhone,
            isReceivedEmail = isReceivedEmail,
            loginFailCount = 0,
        )
    }
}