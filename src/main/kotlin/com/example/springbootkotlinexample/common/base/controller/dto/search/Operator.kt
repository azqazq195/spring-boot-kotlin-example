package com.example.springbootkotlinexample.common.base.controller.dto.search

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import java.time.LocalDateTime

enum class Operator {
    EQUAL {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val key: Expression<Any> = root.get(filter.key)
            return cb.and(cb.equal(key, value), predicate)
        }
    },
    NOT_EQUAL {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val key: Expression<Any> = root.get(filter.key)
            return cb.and(cb.notEqual(key, value), predicate)
        }
    },
    LIKE {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val key: Expression<String> = root.get(filter.key)
            return cb.and(cb.like(cb.upper(key), "%" + value.toString().uppercase() + "%"), predicate)
        }
    },
    IN {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val values: List<Any> = filter.values!!
            val inClause: CriteriaBuilder.In<Any> = cb.`in`(root.get(filter.key))
            for (value in values) {
                inClause.value(filter.fieldType.parse(value.toString()))
            }
            return cb.and(inClause, predicate)
        }
    },
    BETWEEN {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val valueTo: Any = filter.fieldType.parse(filter.valueTo.toString())
            if (filter.fieldType === FieldType.DATE) {
                val startDate: LocalDateTime = value as LocalDateTime
                val endDate: LocalDateTime = valueTo as LocalDateTime
                val key: Expression<LocalDateTime> = root.get(filter.key)
                return cb.and(
                    cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)),
                    predicate
                )
            }
            if (filter.fieldType !== FieldType.CHAR && filter.fieldType !== FieldType.BOOLEAN) {
                val start = value as Number
                val end = valueTo as Number
                val key: Expression<Number> = root.get(filter.key)
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate)
            }
            return predicate
        }
    };

    abstract fun <T> build(
        root: Root<T>,
        cb: CriteriaBuilder,
        filter: FilterDto,
        predicate: Predicate
    ): Predicate
}