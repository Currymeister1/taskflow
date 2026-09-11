package com.curry.taskflow.service

import com.curry.taskflow.api.dto.RegisterUserRequest
import com.curry.taskflow.service.domain.result.AuthRegisterResult

interface AuthService {
    fun registerUser(request: RegisterUserRequest): AuthRegisterResult
}