package com.example.springbootkotlinexample.domain.example.controller

import com.example.springbootkotlinexample.common.base.controller.AbstractCRUDController
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
) : AbstractCRUDController<Example, CreateExampleDto, UpdateExampleDto>(
    exampleService
)