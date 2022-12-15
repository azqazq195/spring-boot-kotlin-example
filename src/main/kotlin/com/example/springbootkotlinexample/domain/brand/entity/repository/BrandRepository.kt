package com.example.springbootkotlinexample.domain.brand.entity.repository

import com.example.springbootkotlinexample.common.generic.entity.repository.BaseRepository
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : BaseRepository<Brand> {
}
