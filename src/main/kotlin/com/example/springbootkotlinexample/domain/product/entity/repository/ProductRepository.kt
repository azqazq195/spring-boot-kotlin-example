package com.example.springbootkotlinexample.domain.product.entity.repository

import com.example.springbootkotlinexample.common.generic.entity.repository.BaseRepository
import com.example.springbootkotlinexample.domain.example.entity.Example
import com.example.springbootkotlinexample.domain.product.entity.Product
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : BaseRepository<Product> {
}