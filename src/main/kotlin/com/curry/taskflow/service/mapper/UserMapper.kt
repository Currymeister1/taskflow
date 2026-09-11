package com.curry.taskflow.service.mapper

import com.curry.taskflow.api.dto.RegisterUserRequest
import com.curry.taskflow.dao.entity.UserEntity
import com.curry.taskflow.service.domain.enums.UserRole
import com.curry.taskflow.service.domain.result.AuthRegisterError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder

fun RegisterUserRequest.toUserEntity(encoder: PasswordEncoder): UserEntity {
    return UserEntity(
        email = this.email,
        hashedPassword = encoder.encode(this.password) ?: throw RuntimeException("Encoding failed"),
        roles = setOf(UserRole.USER)
    )
}

fun AuthRegisterError.toErrorResponse(): ResponseEntity<Any> =
    when (this) {
        AuthRegisterError.EMAIL_ALREADY_REGISTERED -> ResponseEntity.status(HttpStatus.CONFLICT).body(
            ErrorMessageModel(HttpStatus.CONFLICT.value(), errorDescription)
        )
        AuthRegisterError.PASSWORD_DOES_NOT_MEET_CRITERIA -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorMessageModel(HttpStatus.BAD_REQUEST.value(), errorDescription)
        )
    }

class ErrorMessageModel(
    val status: Int? = null,
    val message: String? = null,
    val errors: Map<String, String>? = null,
)