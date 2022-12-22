package com.example.springbootkotlinexample.domain.user.controller.validator

import com.example.springbootkotlinexample.domain.user.service.UserService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotExistsUsernameValidator(
    private val userService: UserService,
) : ConstraintValidator<NotExistsUsername, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        return !userService.isExistsByUsername(value)
    }
}