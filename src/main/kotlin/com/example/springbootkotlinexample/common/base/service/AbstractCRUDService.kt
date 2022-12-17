package com.example.springbootkotlinexample.common.base.service

import com.example.springbootkotlinexample.common.base.controller.dto.ICreateDto
import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.entity.IEntity
import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity
import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.common.advice.exception.InvalidException
import com.example.springbootkotlinexample.common.advice.exception.NotFoundException
import jakarta.transaction.Transactional
import java.lang.reflect.ParameterizedType

abstract class AbstractCRUDService<E>(
    private val repository: IRepository<E>
) : ICRUDService<E> where E : PrimaryKeyEntity, E : IEntity {
    override fun entityName(): String {
        val type = javaClass.genericSuperclass as ParameterizedType
        return (type.actualTypeArguments[0] as Class<*>).simpleName
    }

    override fun find(id: Long): E {
        return repository.findById(id).orElseThrow { NotFoundException(entityName()) }
    }

    override fun findAll(): List<E> =
        repository.findAll()

    @Transactional
    override fun delete(id: Long) {
        // TODO soft-delete 구현
        repository.delete(find(id))
    }

    @Transactional
    override fun <D : IUpdateDto<E>> update(id: Long, updateDto: D) {
        val entity = find(id)
        entity.update(updateDto)
        repository.save(entity)
    }

    @Transactional
    override fun <D : IUpdateDto<E>> updateAll(updateDtoList: List<D>) {
        updateDtoList.map {
            it.id ?: throw InvalidException("id")
            val entity = find(it.id!!)
            entity.update(it)
            repository.save(entity)
        }
    }

    @Transactional
    override fun <D : ICreateDto<E>> create(createDto: D): E =
        repository.save(createDto.toEntity())

    @Transactional
    override fun <D : ICreateDto<E>> createAll(createDtoList: List<D>) {
        repository.saveAll(createDtoList.map { it.toEntity() })
    }
}