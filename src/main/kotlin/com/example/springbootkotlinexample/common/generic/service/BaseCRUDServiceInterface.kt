package com.example.springbootkotlinexample.common.generic.service

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.common.generic.controller.dto.BaseUpdateDto
import com.example.springbootkotlinexample.common.generic.entity.BaseEntity

interface BaseCRUDServiceInterface<E: BaseEntity> {
    fun entityName(): String

    fun find(id: Long): E
    fun findAll(): List<E>
    fun <D : BaseCreateDto<E>> create(createDto: D): E
    fun <D : BaseCreateDto<E>> createAll(createDtoList: List<D>)
    fun <D : BaseUpdateDto<E>> update(id: Long, updateDto: D)
    fun <D : BaseUpdateDto<E>> updateAll(updateDtoList: List<D>)
    fun delete(id: Long)
}