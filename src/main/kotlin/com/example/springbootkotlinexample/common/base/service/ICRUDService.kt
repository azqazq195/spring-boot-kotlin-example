package com.example.springbootkotlinexample.common.base.service

import com.example.springbootkotlinexample.common.base.controller.dto.*
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity

interface ICRUDService<E> where E : PrimaryKeyEntity {
    fun entityName(): String
    fun find(id: Long): E
    fun findAll(): List<E>
    fun create(createDto: AbstractCreateDto<E>): E
    fun <CD : AbstractCreateDto<E>> createAll(createListDto: ValidDtoList<CD>)
    fun update(id: Long, updateDto: AbstractUpdateDto<E>)
    fun <UD : AbstractUpdateDto<E>> updateAll(updateListDto: ValidDtoList<UD>)
    fun delete(id: Long)
}