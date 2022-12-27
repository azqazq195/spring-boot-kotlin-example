package com.example.springbootkotlinexample.common.base.controller.dto.search

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Root

enum class SortDirection {
    ASC {
        override fun <T> build(root: Root<T>, cb: CriteriaBuilder, sort: SortDto): Order {
            return cb.asc(root.get<Any>(sort.key))
        }
    },
    DESC {
        override fun <T> build(root: Root<T>, cb: CriteriaBuilder, sort: SortDto): Order {
            return cb.desc(root.get<Any>(sort.key))
        }
    };

    abstract fun <T> build(root: Root<T>, cb: CriteriaBuilder, sort: SortDto): Order
}