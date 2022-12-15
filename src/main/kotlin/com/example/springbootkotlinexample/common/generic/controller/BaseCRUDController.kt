package com.example.springbootkotlinexample.common.generic.controller

import com.example.springbootkotlinexample.common.generic.controller.dto.BaseCreateDto
import com.example.springbootkotlinexample.common.generic.controller.dto.BaseUpdateDto
import com.example.springbootkotlinexample.common.generic.entity.BaseEntity
import com.example.springbootkotlinexample.common.generic.service.BaseCRUDService
import com.example.springbootkotlinexample.common.generic.response.ResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

open class BaseCRUDController<E : BaseEntity, CD : BaseCreateDto<E>, UD : BaseUpdateDto<E>>(
    private val baseCRUDService: BaseCRUDService<E>
) : BaseCRUDControllerInterface<E> {

    @GetMapping("/{id}")
    override fun find(@PathVariable id: Long): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, baseCRUDService.find(id))
    }

    @GetMapping()
    override fun findAll(): ResponseDto<Any> {
        return ResponseDto(HttpStatus.OK, baseCRUDService.findAll())
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long): ResponseDto<Any> {
        baseCRUDService.delete(id)
        return ResponseDto(HttpStatus.OK)
    }

    @PatchMapping("/{id}")
    override fun <UD : BaseUpdateDto<E>> update(
        @PathVariable id: Long,
        @RequestBody @Valid updateDto: UD
    ): ResponseDto<Any> {
        baseCRUDService.update(id, updateDto)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }

    @PatchMapping()
    override fun <UD : BaseUpdateDto<E>> updateAll(@RequestBody @Valid updateDtoList: List<UD>): ResponseDto<Any> {
        baseCRUDService.updateAll(updateDtoList)
        return ResponseDto(HttpStatus.NO_CONTENT)
    }

    @Operation(summary = "Create", description = "Create")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Created"),
        ApiResponse(responseCode = "400", description = "Bad Request"),
        ApiResponse(responseCode = "500", description = "Internal Server Error"),
    ])
    @PostMapping()
    override fun <CD : BaseCreateDto<E>> create(@RequestBody @Valid createDto: CD): ResponseDto<Any> {
        return ResponseDto(HttpStatus.CREATED, baseCRUDService.create(createDto))
    }

    override fun <CD : BaseCreateDto<E>> createAll(@RequestBody @Valid createDtoList: List<CD>): ResponseDto<Any> {
        baseCRUDService.createAll(createDtoList)
        return ResponseDto(HttpStatus.CREATED)
    }
}