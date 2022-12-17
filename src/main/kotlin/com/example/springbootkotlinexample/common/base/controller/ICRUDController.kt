package com.example.springbootkotlinexample.common.base.controller

import com.example.springbootkotlinexample.common.base.controller.dto.ICreateDto
import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.controller.dto.ValidDtoList
import com.example.springbootkotlinexample.common.base.entity.IEntity
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity
import com.example.springbootkotlinexample.common.base.response.ResponseDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface ICRUDController<E, CD, UD>
        where E : PrimaryKeyEntity, E : IEntity, CD : ICreateDto<E>, UD : IUpdateDto<E> {
    fun find(@PathVariable id: Long): ResponseDto<Any>
    fun findAll(): ResponseDto<Any>
    fun create(@RequestBody createDto: CD): ResponseDto<Any>
    fun createAll(@RequestBody createDtoList: ValidDtoList<CD>): ResponseDto<Any>
    fun update(@PathVariable id: Long, @RequestBody updateDto: UD): ResponseDto<Any>
    fun updateAll(@RequestBody updateDtoList: ValidDtoList<UD>): ResponseDto<Any>
    fun delete(@PathVariable id: Long): ResponseDto<Any>
}