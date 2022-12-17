package com.example.springbootkotlinexample.common.generic.service

import com.example.springbootkotlinexample.common.advice.exception.InvalidException
import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.common.generic.controller.dto.BaseUpdateDto
import com.example.springbootkotlinexample.common.advice.exception.NotFoundException
import com.example.springbootkotlinexample.common.generic.entity.BaseEntity
import com.example.springbootkotlinexample.common.generic.entity.repository.BaseRepository
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import jakarta.transaction.Transactional
import java.lang.reflect.ParameterizedType


open class BaseCRUDService<E : BaseEntity>(
    private val baseRepository: BaseRepository<E>
) : BaseCRUDServiceInterface<E> {

    override fun entityName(): String {
        val type = javaClass.genericSuperclass as ParameterizedType
        return (type.actualTypeArguments[0] as Class<*>).simpleName
    }

    override fun find(id: Long): E  {
        return baseRepository.findById(id).orElseThrow { NotFoundException(entityName()) }
    }

    override fun findAll(): List<E> =
        baseRepository.findAll()

    @Transactional
    override fun delete(id: Long) =
        baseRepository.delete(find(id))

    @Transactional
    override fun <D : BaseUpdateDto<E>> update(id: Long, updateDto: D) {
        baseRepository.save(updateDto.toEntity(find(id)))
    }

    @Transactional
    override fun <D : BaseUpdateDto<E>> updateAll(updateDtoList: List<D>) {
        baseRepository.saveAll(updateDtoList.map {
            if (it.id == null) throw InvalidException("id")
            it.toEntity(find(it.id))
        })
    }

    @Transactional
    override fun <D : BaseCreateDto<E>> create(createDto: D): E =
        baseRepository.save(createDto.toEntity())

    @Transactional
    override fun <D : BaseCreateDto<E>> createAll(createDtoList: List<D>) {
        baseRepository.saveAll(createDtoList.map { it.toEntity() })
    }
}
