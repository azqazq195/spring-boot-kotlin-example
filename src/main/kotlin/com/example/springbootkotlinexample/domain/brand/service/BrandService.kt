package com.example.springbootkotlinexample.domain.brand.service

import com.example.springbootkotlinexample.common.generic.service.BaseCRUDService
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.brand.entity.repository.BrandRepository
import org.springframework.stereotype.Service

@Service
class BrandService(
    brandRepository: BrandRepository
): BaseCRUDService<Brand>(brandRepository)
