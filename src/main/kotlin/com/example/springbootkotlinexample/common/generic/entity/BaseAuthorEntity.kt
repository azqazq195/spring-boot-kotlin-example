package com.example.springbootkotlinexample.common.generic.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**
 * [BaseAuthorEntity]
 * Entity의 생성자와 수정자를 추적하는 엔티티
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseAuthorEntity {
    /**
     * [createdId]
     * * 엔티티가 생성되어 저장될 때 사용자가 자동 저장된다.
     */
    @field:CreatedBy
    @field:Column(nullable = false, updatable = false)
    var createdId: Long? = null
        protected set

    /**
     * [modifiedId]
     * * 조회한 엔티티의 값을 변경할 때 사용자가 자동 저장된다.
     */
    @field:LastModifiedBy
    @field:Column(nullable = false, updatable = true)
    var modifiedId: Long? = null
        protected set
}