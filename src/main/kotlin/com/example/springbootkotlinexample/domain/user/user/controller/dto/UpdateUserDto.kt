package com.example.springbootkotlinexample.domain.user.user.controller.dto

import com.example.springbootkotlinexample.domain.user.user.entity.constants.GenderEnum
import com.example.springbootkotlinexample.domain.user.user.entity.User
import java.util.Date

data class UpdateUserDto(
    val username: String?,
    val password: String?,
    val uuid: String?,
    val name: String?,
    val nickName: String?,
    val phone: String?,
    val address: String?,
    val addressDetail: String?,
    val gender: GenderEnum?,
    val birthDay: Date?,
    val isCheckedPhone: Boolean?,
    val isCheckedEmail: Boolean?,
    val isReceivedPhone: Boolean?,
    val isReceivedEmail: Boolean?,
    val withdrawReason: String?,
    val isBlocked: Boolean?,
    val adminRemark: String?,
)