package com.example.springbootkotlinexample.common.base.controller

import com.example.springbootkotlinexample.common.base.controller.dto.*
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity
import com.example.springbootkotlinexample.common.base.response.ResponseDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface ICRUDController<E, CD, UD>
        where E : PrimaryKeyEntity, CD : AbstractCreateDto<E>, UD : AbstractUpdateDto<E> {
    fun findById(@PathVariable id: Long): ResponseDto<Any>
    fun findAll(): ResponseDto<Any>
    fun create(@RequestBody createDto: CD): ResponseDto<Any>
    fun createAll(@RequestBody createDtoList: ValidDtoList<CD>): ResponseDto<Any>
    fun update(@PathVariable id: Long, @RequestBody updateDto: UD): ResponseDto<Any>
    fun updateAll(@RequestBody updateDtoList: ValidDtoList<UD>): ResponseDto<Any>
    fun delete(@PathVariable id: Long): ResponseDto<Any>
}