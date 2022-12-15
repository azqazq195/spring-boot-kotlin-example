package com.example.springbootkotlinexample.domain.product.service

import com.example.springbootkotlinexample.common.generic.service.BaseCRUDService
import com.example.springbootkotlinexample.domain.product.entity.Product
import com.example.springbootkotlinexample.domain.product.entity.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    productRepository: ProductRepository
): BaseCRUDService<Product>(productRepository)