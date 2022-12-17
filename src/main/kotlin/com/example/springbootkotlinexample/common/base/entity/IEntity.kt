package com.example.springbootkotlinexample.common.base.entity

import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto

interface IEntity {
    fun <UD : IUpdateDto<E>, E> update(dto: UD)
}