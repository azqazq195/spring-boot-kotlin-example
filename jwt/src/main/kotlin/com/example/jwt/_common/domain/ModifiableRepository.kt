package com.example.jwt._common.domain

import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface ModifiableRepository<T : ModifiableEntity> : BaseRepository<T>