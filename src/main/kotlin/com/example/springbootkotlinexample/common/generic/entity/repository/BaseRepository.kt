package com.example.springbootkotlinexample.common.generic.entity.repository


import com.example.springbootkotlinexample.common.generic.entity.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository


interface BaseRepository<E: BaseEntity> : JpaRepository<E, Long>