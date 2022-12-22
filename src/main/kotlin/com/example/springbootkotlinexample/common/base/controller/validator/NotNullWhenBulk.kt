package com.example.springbootkotlinexample.common.base.controller.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotNullWhenBulkValidator::class])
annotation class NotNullWhenBulk(
    val message: String = "{jakarta.validation.constraints.NotNull.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)