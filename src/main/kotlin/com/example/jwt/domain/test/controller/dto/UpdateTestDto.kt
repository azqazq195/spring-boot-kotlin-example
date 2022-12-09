package com.example.jwt.domain.test.controller.dto

import com.example.jwt.common.dto.UpdateDto
import com.example.jwt.domain.test.entity.Test

data class UpdateTestDto(
    val name: String?,
    val comment: String?,
    val count: Int?
) : UpdateDto<Test>() {
    override fun toUpdatedEntity(entity: Test): Test = Test(
        id = entity.id,
        name = name ?: entity.name,
        comment = comment ?: entity.comment,
        count = count ?: entity.count
    )
}