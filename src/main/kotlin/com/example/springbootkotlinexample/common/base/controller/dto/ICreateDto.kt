package com.example.springbootkotlinexample.common.base.controller.dto

import com.example.springbootkotlinexample.common.base.entity.IEntity

interface ICreateDto<E: IEntity> {
    fun toEntity(): E
}