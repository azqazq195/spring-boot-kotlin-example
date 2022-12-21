package com.example.springbootkotlinexample.domain.product.controller

import com.example.springbootkotlinexample.common.base.controller.AbstractCRUDController
import com.example.springbootkotlinexample.domain.product.controller.dto.CreateProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.ReadProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import com.example.springbootkotlinexample.domain.product.service.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    productService: ProductService
) : AbstractCRUDController<Product, CreateProductDto, UpdateProductDto, ReadProductDto>(
    productService
)
