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
) {
    fun toEntity(entity: User): User = entity.copy(
        username = username ?: entity.username,
        password = password ?: entity.password,
        name = name ?: entity.name,
        nickName = nickName ?: entity.nickName,
        phone = phone ?: entity.phone,
        address = address ?: entity.address,
        addressDetail = addressDetail ?: entity.addressDetail,
        gender = gender ?: entity.gender,
        birthDay = birthDay ?: entity.birthDay,
        isCheckedPhone = isCheckedPhone ?: entity.isCheckedPhone,
        isCheckedEmail = isCheckedEmail ?: entity.isCheckedEmail,
        isReceivedPhone = isReceivedPhone ?: entity.isReceivedPhone,
        isReceivedEmail = isReceivedEmail ?: entity.isReceivedEmail,
        withdrawReason = withdrawReason ?: entity.withdrawReason,
        isBlocked = isBlocked ?: entity.isBlocked,
        adminRemark = adminRemark ?: entity.adminRemark,
    )
}