package com.example.springbootkotlinexample.domain.brand.entity

data class Brand(
    val id: Long? = null,
    val name: String,
    val description: String,
    val openCount: Int,
) {
    fun addOpenCount() = copy(openCount = openCount + 1)
}