package com.example.springbootkotlinexample.common.base.entity.repository

import com.example.springbootkotlinexample.common.base.entity.PrimaryKeyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IRepository<E> : JpaRepository<E, Long> where E : PrimaryKeyEntity