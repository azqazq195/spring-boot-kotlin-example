package com.example.springbootkotlinexample.domain.example.service

import com.example.springbootkotlinexample.common.generic.service.BaseCRUDService
import com.example.springbootkotlinexample.domain.example.entity.Example
import com.example.springbootkotlinexample.domain.example.entity.repository.ExampleRepository
import org.springframework.stereotype.Service

@Service
class ExampleService(
    exampleRepository: ExampleRepository
): BaseCRUDService<Example>(exampleRepository)