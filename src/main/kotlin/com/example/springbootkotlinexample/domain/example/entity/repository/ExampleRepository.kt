package com.example.springbootkotlinexample.domain.example.entity.repository

import com.example.springbootkotlinexample.common.generic.entity.repository.BaseRepository
import com.example.springbootkotlinexample.domain.example.entity.Example
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepository : BaseRepository<Example> {
}