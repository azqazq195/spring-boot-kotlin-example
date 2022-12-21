package com.example.springbootkotlinexample.common.base.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractAuditingEntity : PrimaryKeyEntity() {
    /**
     * [createdAt]
     * * 엔티티가 생성되어 저장될 때 시간이 자동 저장된다.
     * * @CreatedDate
     * * @Column(nullable = false, updatable = false)
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null

    /**
     * [modifiedAt]
     * * 조회한 엔티티의 값을 변경할 때 시간이 자동 저장된다.
     * * @LastModifiedDate
     * * @Column(nullable = false, updatable = true)
     */
    @LastModifiedDate
    @Column(nullable = false, updatable = true)
    var modifiedAt: LocalDateTime? = null

    /**
     * [deletedAt]
     * * 엔티티가 삭제되었을 때 시간이 자동 저장된다. (soft-delete)
     * * @Column(nullable = true, updatable = true)
     */
    @Column(nullable = true, updatable = true)
    var deletedAt: LocalDateTime? = null

    /**
     * [createdId]
     * * 엔티티가 생성되어 저장될 때 사용자가 자동 저장된다.
     */

    @CreatedBy
//    @Column(nullable = false, updatable = false)
    @Column(nullable = true, updatable = false)
    var createdId: Long? = null

    /**
     * [modifiedId]
     * * 조회한 엔티티의 값을 변경할 때 사용자가 자동 저장된다.
     */
    @LastModifiedBy
//    @Column(nullable = false, updatable = true)
    @Column(nullable = true, updatable = true)
    var modifiedId: Long? = null
}