package com.example.springbootkotlinexample.common.contoller

import com.example.springbootkotlinexample.common.dto.CreateDto
import com.example.springbootkotlinexample.common.dto.ResponseDto
import com.example.springbootkotlinexample.common.dto.UpdateDto
import org.springframework.web.bind.annotation.*

// 잘안된다. CreateDto<T> 쪽 확인
// extends class 에서 @PathVariable 사용시 경고문 제거 확인
abstract class BaseController<T> {
    @GetMapping("/{id}")
    abstract fun find(@PathVariable id: Long): ResponseDto<T>

    @GetMapping()
    abstract fun findAll(): ResponseDto<List<T>>

    @PostMapping()
    abstract fun create(@RequestBody createDto: CreateDto<T>): ResponseDto<T>

    @PatchMapping("/{id}")
    abstract fun patch(@PathVariable id: Long, @RequestBody updateDto: UpdateDto<T>): ResponseDto<T>

}