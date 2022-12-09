package com.example.jwt.domain.test.controller

import com.example.jwt.common.dto.ResponseDto
import com.example.jwt.domain.test.controller.dto.CreateTestDto
import com.example.jwt.domain.test.controller.dto.UpdateTestDto
import com.example.jwt.domain.test.entity.Test
import com.example.jwt.domain.test.service.TestService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/test")
class TestController(
    private val testService: TestService
) {
    @GetMapping()
    fun findAll(): ResponseDto<List<Test>> {
        val testList = testService.findAll()
        return ResponseDto(HttpStatus.OK, testList)
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): ResponseDto<Test> {
        val test = testService.findById(id)
        return ResponseDto(HttpStatus.OK, test)
    }

    @PostMapping()
    fun create(@RequestBody createTestDto: CreateTestDto): ResponseDto<Test> {
        val test = testService.create(createTestDto)
        return ResponseDto(HttpStatus.CREATED, test)
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Long, @RequestBody updateTestDto: UpdateTestDto): ResponseDto<Test> {
        testService.update(id, updateTestDto)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }
}