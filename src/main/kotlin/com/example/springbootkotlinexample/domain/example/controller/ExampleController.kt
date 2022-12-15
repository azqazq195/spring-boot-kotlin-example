package com.example.springbootkotlinexample.domain.example.controller

import com.example.springbootkotlinexample.common.generic.controller.BaseCRUDController
import com.example.springbootkotlinexample.domain.example.controller.dto.CreateExampleDto
import com.example.springbootkotlinexample.domain.example.controller.dto.UpdateExampleDto
import com.example.springbootkotlinexample.domain.example.entity.Example
import com.example.springbootkotlinexample.domain.example.service.ExampleService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/example")
class ExampleController(
    exampleService: ExampleService
): BaseCRUDController<Example, CreateExampleDto, UpdateExampleDto>(exampleService) {
}