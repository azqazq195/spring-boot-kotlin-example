package com.example.springbootkotlinexample.common.base.controller.dto.search

data class FilterDto(
    val key: String,
    val operator: Operator,
    val fieldType: FieldType,
    val value: Any? = null,
    val valueTo: Any? = null,
    val values: List<Any>? = null
)