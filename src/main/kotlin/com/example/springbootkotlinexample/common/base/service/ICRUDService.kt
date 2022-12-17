package com.example.springbootkotlinexample.common.base.service

import com.example.springbootkotlinexample.common.base.controller.dto.ICreateDto
import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.controller.dto.ValidDtoList
import com.example.springbootkotlinexample.common.base.entity.IEntity
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity

interface ICRUDService<E> where E : PrimaryKeyEntity, E : IEntity {
    fun entityName(): String
    fun find(id: Long): E
    fun findAll(): List<E>
    fun create(createDto: ICreateDto<E>): E
    fun <CD : ICreateDto<E>> createAll(createListDto: ValidDtoList<CD>)
    fun update(id: Long, updateDto: IUpdateDto<E>)
    fun <UD : IUpdateDto<E>> updateAll(updateListDto: ValidDtoList<UD>)
    fun delete(id: Long)
}