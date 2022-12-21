package com.example.springbootkotlinexample.domain.brand.service

import com.example.springbootkotlinexample.common.base.service.AbstractCRUDService
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.brand.entity.repository.BrandRepository
import org.springframework.stereotype.Service

@Service
class BrandService(
    brandRepository: BrandRepository
): AbstractCRUDService<Brand>(brandRepository)
