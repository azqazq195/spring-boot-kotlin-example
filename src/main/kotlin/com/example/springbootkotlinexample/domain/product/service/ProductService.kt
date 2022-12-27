package com.example.springbootkotlinexample.domain.product.service

import com.example.springbootkotlinexample.common.base.service.AbstractService
import com.example.springbootkotlinexample.domain.product.controller.dto.CreateProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.ReadProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import com.example.springbootkotlinexample.domain.product.entity.mapper.ProductMapper
import com.example.springbootkotlinexample.domain.product.entity.repository.ProductRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productMapper: ProductMapper,
    private val productRepository: ProductRepository,
    private val jpaQueryFactory: JPAQueryFactory,
    ) : AbstractService<Product, CreateProductDto, UpdateProductDto, ReadProductDto>(
    productMapper,
    productRepository,
    jpaQueryFactory,
)