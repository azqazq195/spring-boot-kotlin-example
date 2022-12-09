package com.example.jwt.domain.test.repository

import com.example.jwt.domain.test.entity.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : JpaRepository<Test, Long> {
}