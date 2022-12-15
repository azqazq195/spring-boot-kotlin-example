package com.example.springbootkotlinexample.domain.test.controller.dto

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.domain.test.entity.Test
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTestDto(
    val name: String?,
    @field:NotBlank(message = "공백 안됨.")
    @field:Size(min= 1, max = 10, message = "1~10자 사이.")
    val comment: String,
    @field:Min(value = 1, message = "1 이상.")
    val count: Int
) : BaseCreateDto<Test>() {
    override fun toEntity(): Test {
        return Test(
            name = name,
            comment = comment,
            count = count
        )
    }
}