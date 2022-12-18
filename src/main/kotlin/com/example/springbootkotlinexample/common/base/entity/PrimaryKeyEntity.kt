package com.example.springbootkotlinexample.common.base.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import jakarta.persistence.*

@MappedSuperclass
abstract class PrimaryKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    abstract fun <UD : AbstractUpdateDto<E>, E> update(dto: UD)
}

//@MappedSuperclass
//abstract class PrimaryKeyEntity : Persistable<UUID> {
//    @Id
//    @Column(columnDefinition = "uuid")
//    private val id: UUID = UlidCreator.getMonotonicUlid().toUuid()
//
//    @Transient
//    private var _isNew = true
//
//    override fun getId(): UUID = id
//
//    override fun isNew(): Boolean = _isNew
//
//    override fun equals(other: Any?): Boolean {
//        if (other == null) {
//            return false
//        }
//
//        if (other !is HibernateProxy && this::class != other::class) {
//            return false
//        }
//
//        return id == getIdentifier(other)
//    }
//
//    private fun getIdentifier(obj: Any): Any? {
//        return if (obj is HibernateProxy) {
//            obj.hibernateLazyInitializer.identifier
//        } else {
//            (obj as PrimaryKeyEntity).id
//        }
//    }
//
//    override fun hashCode() = Objects.hashCode(id)
//
//    @PostPersist
//    @PostLoad
//    protected fun load() {
//        _isNew = false
//    }
//}