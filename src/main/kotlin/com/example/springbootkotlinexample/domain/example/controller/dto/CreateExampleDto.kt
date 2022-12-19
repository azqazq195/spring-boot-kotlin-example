package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractCreateDto
import com.example.springbootkotlinexample.domain.example.entity.Example
import com.example.springbootkotlinexample.domain.example.entity.constants.ExampleEnum
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * field에 null이 허용되는 이유
 * * null이 허용되지 않는 경우 constuctor에서 null을 받을 수 없음
 * * 생성자체가 되지 않아 validation 에러를 발생시킬 수 없다.
 * * @NotNull annotation을 활용하여 검사한다.
 */
class CreateExampleDto(
    @field:NotNull
    @field:Size(min = 2, max = 5)
    val name: String?,

    @field:NotNull
    @field:Size(min = 2, max = 100)
    val content: String?,

    @field:NotNull
    @field:Max(100)
    val count: Int?,

    @field:NotNull
    val enum: ExampleEnum?,
) : AbstractCreateDto<Example>() {
    override fun toEntity(): Example {
        return Example(
            name = name!!,
            content = content!!,
            count = count!!,
            enum = enum!!,
        )
    }
}