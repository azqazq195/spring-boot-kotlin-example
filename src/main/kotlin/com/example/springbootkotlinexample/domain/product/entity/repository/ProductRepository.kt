package com.example.springbootkotlinexample.domain.product.entity.repository

import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.domain.product.entity.Product
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : IRepository<Product> {
}