package com.example.springbootkotlinexample.common.base.service

interface IService<E, CD, UD, RD> {
    fun entityClass(): Class<*>
    fun entityName(): String
    fun findById(id: Long): RD
    fun findAll(): List<RD>
    fun create(createDto: CD): RD
    fun createAll(createListDto: List<CD>)
    fun update(id: Long, updateDto: UD)
    fun updateAll(updateListDto: List<UD>)
    fun deleteById(id: Long)
}