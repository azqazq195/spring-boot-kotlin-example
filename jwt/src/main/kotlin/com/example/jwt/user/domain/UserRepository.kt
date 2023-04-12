package com.example.jwt.user.domain

import com.example.jwt._common.domain.DeletableRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : DeletableRepository<User>
