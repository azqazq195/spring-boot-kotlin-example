package com.example.springbootkotlinexample.common.base.controller.dto.search

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification


class SearchSpecification<T>(
    @Transient
    val searchDto: SearchDto,
) : Specification<T> {

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate {
        var predicate: Predicate = cb.equal(cb.literal(true), true)
        searchDto.filters?.let {
            for (filter in it) {
                predicate = filter.operator.build(root, cb, filter, predicate)
            }
        }
        val orders: MutableList<Order> = ArrayList<Order>()
        searchDto.sorts?.let {
            for (sort in searchDto.sorts) {
                orders.add(sort.direction.build(root, cb, sort))
            }
        }
        query.orderBy(orders)
        return predicate
    }

    fun getPageable(): Pageable {
        return PageRequest.of(searchDto.page, searchDto.size)
    }
}