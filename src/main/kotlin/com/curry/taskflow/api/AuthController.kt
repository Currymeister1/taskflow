package com.curry.taskflow.api

import com.curry.taskflow.api.dto.RegisterUserRequest
import com.curry.taskflow.service.UserService
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
    private val userService: UserService,
) {

    @PostMapping("/register")
    fun register(@RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<Any> {
        return when (val authResult = userService.registerUser(registerUserRequest)) {
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
                AuthRegisterError.EMAIL_ALREADY_REGISTERED -> authResult.authRegisterError.toErrorResponse()
                AuthRegisterError.PASSWORD_DOES_NOT_MEET_CRITERIA -> authResult.authRegisterError.toErrorResponse()
            }
        }
    }
}