package com.example.springbootkotlinexample.domain.brand.entity.repository

import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : IRepository<Brand>
