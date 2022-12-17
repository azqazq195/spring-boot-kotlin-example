package com.example.springbootkotlinexample.domain.product.controller

import com.example.springbootkotlinexample.common.base.controller.AbstractCRUDController
import com.example.springbootkotlinexample.common.generic.response.ResponseDto
import com.example.springbootkotlinexample.common.advice.exception.UnsupportedOperationException
import com.example.springbootkotlinexample.domain.product.controller.dto.CreateProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import com.example.springbootkotlinexample.domain.product.service.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    productService: ProductService
): AbstractCRUDController<Product, CreateProductDto, UpdateProductDto>(productService) {
    override fun createAll(createDtoList: List<CreateProductDto>): ResponseDto<Any> {
        throw UnsupportedOperationException()
    }
}