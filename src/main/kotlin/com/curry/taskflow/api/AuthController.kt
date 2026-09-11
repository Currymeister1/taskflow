package com.curry.taskflow.api

import com.curry.taskflow.api.dto.RegisterUserRequest
import com.curry.taskflow.service.AuthService
import com.curry.taskflow.service.domain.result.AuthRegisterError
import com.curry.taskflow.service.domain.result.AuthRegisterResult
import com.curry.taskflow.service.mapper.toErrorResponse
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
@RequestMapping(value = ["${ApiConstants.ROOT_PATH}${ApiConstants.API_VERSION}/auth"], produces = ["application/json"])
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/register")
    fun register(@RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<Any> {
        return when (val authResult = authService.registerUser(registerUserRequest)) {
            is AuthRegisterResult.Success -> {
                ResponseEntity
                    .status(HttpStatus.CREATED.value())
                    .body(
                        mapOf(
                            "email" to registerUserRequest.email
                        )
                    )
            }

            is AuthRegisterResult.Failure -> when (authResult.authRegisterError) {
                AuthRegisterError.EMAIL_ALREADY_REGISTERED -> AuthRegisterError.EMAIL_ALREADY_REGISTERED.toErrorResponse()
                AuthRegisterError.PASSWORD_DOES_NOT_MEET_CRITERIA -> AuthRegisterError.EMAIL_ALREADY_REGISTERED.toErrorResponse()
            }
        }
    }
}