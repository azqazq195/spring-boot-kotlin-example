package com.example.springbootkotlinexample.common.base.controller.dto.search

data class SearchDto(
    val filters: List<FilterDto>? = null,
    val sorts: List<SortDto>? = null,
    val page: Int = 0,
    val size: Int = 20,
)
