package com.example.jwt.domain.test.controller.dto

import com.example.jwt.common.dto.CreateDto
import com.example.jwt.domain.test.entity.Test

data class CreateTestDto(
    val name: String?,
    val comment: String,
    val count: Int
) : CreateDto<Test>() {
    override fun toEntity(): Test {
        return Test(
            name = name,
            comment = comment,
            count = count
        )
    }
}