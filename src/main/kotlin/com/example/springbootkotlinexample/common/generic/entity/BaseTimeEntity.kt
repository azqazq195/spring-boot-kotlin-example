package com.example.springbootkotlinexample.common.generic.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 * [BaseTimeEntity]
 * Entity의 생성일과 수정일을 추적하는 엔티티
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity {
    /**
     * [createdAt]
     * * 엔티티가 생성되어 저장될 때 시간이 자동 저장된다.
     */
    @field:CreatedDate
    @field:Column(nullable = false, updatable = false)
    lateinit var createdAt: LocalDateTime
        protected set

    /**
     * [modifiedAt]
     * * 조회한 엔티티의 값을 변경할 때 시간이 자동 저장된다.
     */
    @field:LastModifiedDate
    @field:Column(nullable = false, updatable = true)
    lateinit var modifiedAt: LocalDateTime
        protected set

    /**
     * [deletedAt]
     * * 엔티티가 삭제되었을 때 시간이 자동 저장된다. (soft-delete)
     */
    @field:Column(nullable = true, updatable = true)
    var deletedAt: LocalDateTime? = null
        protected set
}