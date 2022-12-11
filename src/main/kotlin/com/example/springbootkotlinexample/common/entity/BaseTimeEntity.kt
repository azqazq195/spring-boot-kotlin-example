package com.example.springbootkotlinexample.common.entity

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
     * * UpdateDto를 통해 Entity를 업데이트 하는 경우 null로 초기화된다.
     * * updatable = false 옵션을 통해 이를 방지한다.
     */
    @field:CreatedDate
    @field:Column(nullable = false, updatable = false)
    lateinit var createdAt: LocalDateTime
        protected set

    /**
     * [modifiedAt]
     * * 조회한 엔티티의 값을 변경할 때 시간이 자동 저장된다.
     * * UpdateDto를 통해 Entity를 업데이트 하는 경우 null로 초기화 되어도 무방하다.
     */
    @field:LastModifiedDate
    @field:Column(nullable = false, updatable = true)
    lateinit var modifiedAt: LocalDateTime
        protected set

    /**
     * [deletedAt]
     * * 엔티티가 삭제되었을 때 시간이 자동 저장된다. (soft-delete)
     * * UpdateDto를 통해 Entity를 업데이트 하는 경우 null로 초기화된다.
     * ! 삭제한 Entity는 수정 불가함으로 별도의 설정은 없지만, 수정시 deletedAt 값이 null로 초기화 된다.
     */
    @field:Column(nullable = true, updatable = true)
    var deletedAt: LocalDateTime? = null
        protected set
}