package com.example.springbootkotlinexample.domain.brand.controller

import com.example.springbootkotlinexample.common.generic.controller.BaseCRUDController
import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.common.generic.response.ResponseDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.CreateBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.UpdateBrandDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.brand.service.BrandService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/api/v1/brand")
class BrandController(
    brandService: BrandService
):BaseCRUDController<Brand, CreateBrandDto, UpdateBrandDto>(brandService) {
}