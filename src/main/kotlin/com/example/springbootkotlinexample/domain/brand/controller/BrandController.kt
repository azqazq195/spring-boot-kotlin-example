package com.example.springbootkotlinexample.domain.brand.controller

import com.example.springbootkotlinexample.domain.brand.controller.dto.CreateBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.ReadBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.UpdateBrandDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.brand.service.BrandService
import com.example.springbootkotlinexample.common.base.controller.AbstractController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/brand")
class BrandController(
    private val brandService: BrandService
) : AbstractController<Brand, CreateBrandDto, UpdateBrandDto, ReadBrandDto>(
    brandService
)
