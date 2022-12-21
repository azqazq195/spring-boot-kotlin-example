#!/bin/bash

RED="\033[1;31""m"
GREEN="\033[1;32""m"
YELLOW="\033[1;33""m"
NC="\033[0m"

echo "${YELLOW} > input domain name${NC}"
read -r DOMAIN_NAME
FILE_NAME="$(tr '[:lower:]' '[:upper:]' <<< ${DOMAIN_NAME:0:1})${DOMAIN_NAME:1}"

cd src
cd main
cd kotlin
cd com
cd example
cd springbootkotlinexample
cd domain

mkdir "$DOMAIN_NAME"
echo "${GREEN} > created ${DOMAIN_NAME} ${NC}"

cd $DOMAIN_NAME

mkdir -p "controller/dto"

### Controller
echo "package com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.controller

import com.example.springbootkotlinexample.common.base.controller.AbstractCRUDController
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.controller.dto.Create${FILE_NAME}Dto
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.controller.dto.Update${FILE_NAME}Dto
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity.${FILE_NAME}
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.service.${FILE_NAME}Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(\"/api/v1/${DOMAIN_NAME}\")
class ${FILE_NAME}Controller(
<<<<<<< Updated upstream
    val ${DOMAIN_NAME}Service: ${FILE_NAME}Service
=======
    private val ${DOMAIN_NAME}Service: ${FILE_NAME}Service
>>>>>>> Stashed changes
) : AbstractCRUDController<${FILE_NAME}, Create${FILE_NAME}Dto, Update${FILE_NAME}Dto>(
    ${DOMAIN_NAME}Service
)" > "controller/${FILE_NAME}Controller.kt"

### CreateDTO
echo "package com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractCreateDto
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity.${FILE_NAME}
import jakarta.validation.constraints.NotNull

class Create${FILE_NAME}Dto(
    @field:NotNull
    val name: String?,
) : AbstractCreateDto<${FILE_NAME}>() {
    override fun toEntity(): ${FILE_NAME} {
        return ${FILE_NAME}(
            name = name!!,
        )
    }
}" > "controller/dto/Create${FILE_NAME}Dto.kt"

### UpdateDTO
echo "package com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.controller.dto

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity.${FILE_NAME}

class Update${FILE_NAME}Dto(
    val name: String?,
) : AbstractUpdateDto<${FILE_NAME}>()" > "controller/dto/Update${FILE_NAME}Dto.kt"
echo "${GREEN} > created controller ${NC}"

mkdir -p "entity/repository"

### Entity
echo "package com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity

import com.example.springbootkotlinexample.common.base.controller.dto.AbstractUpdateDto
import com.example.springbootkotlinexample.common.base.entity.AbstractAuditingEntity
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.controller.dto.Update${FILE_NAME}Dto
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = \"tb_${DOMAIN_NAME}\")
class ${FILE_NAME}(
    name: String,
) : AbstractAuditingEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    override fun <UD : AbstractUpdateDto<E>, E> update(dto: UD) {
        val update${FILE_NAME}Dto = dto as Update${FILE_NAME}Dto
        this.name = update${FILE_NAME}Dto.name ?: this.name
    }
}" > "entity/${FILE_NAME}.kt"

### Repository
echo "package com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity.repository

import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity.${FILE_NAME}
import org.springframework.stereotype.Repository

@Repository
interface ${FILE_NAME}Repository : IRepository<${FILE_NAME}>" > "entity/repository/${FILE_NAME}Repository.kt"
echo "${GREEN} > created entity ${NC}"

mkdir "service"

### Service
echo "package com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.service

import com.example.springbootkotlinexample.common.base.service.AbstractCRUDService
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity.${FILE_NAME}
import com.example.springbootkotlinexample.domain.${DOMAIN_NAME}.entity.repository.${FILE_NAME}Repository
import org.springframework.stereotype.Service

@Service
class ${FILE_NAME}Service(
<<<<<<< Updated upstream
    val ${DOMAIN_NAME}Repository: ${FILE_NAME}Repository
=======
    private val ${DOMAIN_NAME}Repository: ${FILE_NAME}Repository
>>>>>>> Stashed changes
) : AbstractCRUDService<${FILE_NAME}>(${DOMAIN_NAME}Repository)" > "service/${FILE_NAME}Service.kt"
echo "${GREEN} > created service ${NC}"

echo "${GREEN} > done ${NC}"
