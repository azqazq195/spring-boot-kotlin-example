package com.example.springbootkotlinexample.domain.brand.entity.repository

import com.example.springbootkotlinexample.domain.brand.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : JpaRepository<Brand, Long> {

}