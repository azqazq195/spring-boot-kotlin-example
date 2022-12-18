package com.example.springbootkotlinexample.common.base.service

import com.example.springbootkotlinexample.common.base.controller.dto.*
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity
import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.common.base.service.exception.NotFoundEntityException
import com.example.springbootkotlinexample.common.base.service.exception.RequiredArgumentException
import jakarta.transaction.Transactional
import java.lang.reflect.ParameterizedType

abstract class AbstractCRUDService<E>(
    private val repository: IRepository<E>
) : ICRUDService<E> where E : PrimaryKeyEntity {
    override fun entityName(): String {
        val type = javaClass.genericSuperclass as ParameterizedType
        return (type.actualTypeArguments[0] as Class<*>).simpleName
    }

    override fun find(id: Long): E {
        return repository.findById(id).orElseThrow { NotFoundEntityException(entityName()) }
    }

    override fun findAll(): List<E> =
        repository.findAll()

    @Transactional
    override fun create(createDto: AbstractCreateDto<E>): E =
        repository.save(createDto.toEntity())

    @Transactional
    override fun <CD : AbstractCreateDto<E>> createAll(createListDto: ValidDtoList<CD>) {
        createListDto.list!!.forEach { create(it) }
    }

    @Transactional
    override fun update(id: Long, updateDto: AbstractUpdateDto<E>) {
        val entity = find(id)
        entity.update(updateDto)
        repository.save(entity)
    }

    @Transactional
    override fun <UD : AbstractUpdateDto<E>> updateAll(updateListDto: ValidDtoList<UD>) {
        updateListDto.list!!.forEach {
            it.id ?: throw RequiredArgumentException("id")
            update(it.id!!, it)
        }
    }

    @Transactional
    override fun delete(id: Long) {
        // TODO soft-delete 구현
        repository.delete(find(id))
    }

}