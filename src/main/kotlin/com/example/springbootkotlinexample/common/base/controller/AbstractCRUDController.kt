package com.example.springbootkotlinexample.common.base.controller

import com.example.springbootkotlinexample.common.base.controller.dto.*
import com.example.springbootkotlinexample.common.base.entity.IEntity
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity
import com.example.springbootkotlinexample.common.base.response.ResponseDto
import com.example.springbootkotlinexample.common.base.service.ICRUDService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


abstract class AbstractCRUDController<E, CD, UD>(
    private val service: ICRUDService<E>
) : ICRUDController<E, CD, UD> where E : PrimaryKeyEntity, E : IEntity, CD : ICreateDto<E>, UD : IUpdateDto<E> {
    @GetMapping("/{id}")
    override fun find(@PathVariable id: Long): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, service.find(id))
    }

    @GetMapping()
    override fun findAll(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, service.findAll())
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long): ResponseDto<Any> {
        service.delete(id)
        return ResponseDto(HttpStatus.OK)
    }

    @PostMapping()
    override fun create(@RequestBody @Valid createDto: CD): ResponseDto<Any> {
        return ResponseDto(HttpStatus.CREATED, service.create(createDto))
    }

    @PostMapping("/bulk")
    override fun createAll(@RequestBody @Valid createDtoList: ValidDtoList<CD>): ResponseDto<Any> {
        service.createAll(createDtoList)
        return ResponseDto(HttpStatus.CREATED)
    }

    @PatchMapping("/{id}")
    override fun update(@PathVariable id: Long, @RequestBody @Valid updateDto: UD): ResponseDto<Any> {
        service.update(id, updateDto)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/bulk")
    override fun updateAll(@RequestBody @Valid updateDtoList: ValidDtoList<UD>): ResponseDto<Any> {
        service.updateAll(updateDtoList)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }
}