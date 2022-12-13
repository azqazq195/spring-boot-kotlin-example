package com.example.springbootkotlinexample.domain.brand.service

import com.example.springbootkotlinexample.common.exception.NotFoundException
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.brand.entity.repository.BrandRepository

class BrandService(
    private val brandRepository: BrandRepository
) {
    fun findAll() = brandRepository.findAll()

    fun findById(id: Long) = brandRepository.findById(id).orElseThrow{ NotFoundException("브랜드") }

    fun create(brand: Brand) = brandRepository.save(brand)

    fun update(id: Long, brand: Brand) = brandRepository.save(brand.copy(id = id))
}