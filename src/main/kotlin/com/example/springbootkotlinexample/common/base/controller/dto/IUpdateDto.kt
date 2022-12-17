package com.example.springbootkotlinexample.common.base.controller.dto

import com.example.springbootkotlinexample.common.base.entity.IEntity

interface IUpdateDto<E: IEntity> {
    // bulk patch 를 위한 id
    val id: Long?
}