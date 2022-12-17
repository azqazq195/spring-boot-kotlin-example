package com.example.springbootkotlinexample.domain.example.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.domain.example.entity.Example
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Size

class UpdateExampleDto(
    override val id: Long?,

    @field:Size(min = 2, max = 5)
    val name: String?,

    @field:Size(min = 2, max = 100)
    val content: String?,

    @field:Max(100)
    val count: Int?,
) : IUpdateDto<Example>
