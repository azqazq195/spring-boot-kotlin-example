package com.example.springbootkotlinexample.common.generic.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass


@MappedSuperclass
abstract class BaseEntity(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null
)