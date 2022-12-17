package com.example.springbootkotlinexample.common.base.service

import com.example.springbootkotlinexample.common.base.controller.dto.ICreateDto
import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.entity.IEntity
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity

interface ICRUDService<E> where E : PrimaryKeyEntity, E : IEntity {
    fun entityName(): String
    fun find(id: Long): E
    fun findAll(): List<E>
    fun <CD : ICreateDto<E>> create(createDto: CD): E
    fun <CD : ICreateDto<E>> createAll(createDtoList: List<CD>)
    fun <UD : IUpdateDto<E>> update(id: Long, updateDto: UD)
    fun <UD : IUpdateDto<E>> updateAll(updateDtoList: List<UD>)
    fun delete(id: Long)
}