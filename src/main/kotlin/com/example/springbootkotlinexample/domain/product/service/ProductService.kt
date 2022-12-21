package com.example.springbootkotlinexample.domain.product.service

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractCreateDto
import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.service.AbstractCRUDService
import com.example.springbootkotlinexample.domain.brand.service.BrandService
import com.example.springbootkotlinexample.domain.product.controller.dto.CreateProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import com.example.springbootkotlinexample.domain.product.entity.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val brandService: BrandService,
    private val productRepository: ProductRepository
) : AbstractCRUDService<Product>(productRepository) {
    override fun create(createDto: AbstractCreateDto<Product>): Product {
        val createProductDto = createDto as CreateProductDto
        val product = createProductDto.toEntity()
        val brand = brandService.findById(createProductDto.brandId!!)
        product.setBrand(brand)
        return productRepository.save(product)
    }

    override fun update(id: Long, updateDto: AbstractUpdateDto<Product>) {
        val entity = findById(id)
        entity.update(updateDto)

        val updateProductDto = updateDto as UpdateProductDto
        updateProductDto.brandId?.let { brandId ->
            val brand = brandService.findById(brandId)
            entity.setBrand(brand)
        }
        productRepository.save(entity)
    }
}
