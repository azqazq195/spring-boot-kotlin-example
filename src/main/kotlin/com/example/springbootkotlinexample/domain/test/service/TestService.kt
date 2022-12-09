package com.example.springbootkotlinexample.domain.test.service

import com.example.springbootkotlinexample.domain.test.controller.dto.CreateTestDto
import com.example.springbootkotlinexample.domain.test.controller.dto.UpdateTestDto
import com.example.springbootkotlinexample.domain.test.entity.Test
import com.example.springbootkotlinexample.domain.test.exception.NotFoundTestException
import com.example.springbootkotlinexample.domain.test.repository.TestRepository
import org.springframework.stereotype.Service

@Service
class TestService(
    private val testRepository: TestRepository
) {
    fun findAll(): List<Test> {
        return testRepository.findAll()
    }

    fun findById(id: Long): Test {
        return testRepository.findById(id).orElseThrow { NotFoundTestException() }
    }

    fun create(createTestDto: CreateTestDto): Test {
        return testRepository.save(createTestDto.toEntity())
    }

    fun update(targetId: Long, updateTestDto: UpdateTestDto): Test {
        val target = findById(targetId);
        val test = updateTestDto.toUpdatedEntity(target)
        return testRepository.save(test);
    }
}