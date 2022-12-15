package com.example.springbootkotlinexample.common.generic.controller

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.common.generic.controller.dto.BaseUpdateDto
import com.example.springbootkotlinexample.common.generic.entity.BaseEntity
import com.example.springbootkotlinexample.common.generic.response.ResponseDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface BaseCRUDControllerInterface<E: BaseEntity> {
    fun find(@PathVariable id: Long): ResponseDto<Any>
    fun findAll(): ResponseDto<Any>
    fun <D : BaseCreateDto<E>> create(@RequestBody createDto: D): ResponseDto<Any>
    fun <D : BaseCreateDto<E>> createAll(@RequestBody createDtoList: List<D>): ResponseDto<Any>
    fun <D : BaseUpdateDto<E>> update(@PathVariable id: Long, @RequestBody updateDto: D): ResponseDto<Any>
    fun <D : BaseUpdateDto<E>> updateAll(@RequestBody updateDtoList: List<D>): ResponseDto<Any>
    fun delete(@PathVariable id: Long): ResponseDto<Any>
}