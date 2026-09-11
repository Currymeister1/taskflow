package com.curry.taskflow.service.impl

import com.curry.taskflow.api.dto.RegisterUserRequest
import com.curry.taskflow.dao.repo.UserRepository
import com.curry.taskflow.service.AuthService
import com.curry.taskflow.service.domain.result.AuthRegisterError
import com.curry.taskflow.service.domain.result.AuthRegisterResult
import com.curry.taskflow.service.mapper.toUserEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl (
    private val userRepository: UserRepository
) : AuthService {
    private val passwordRegex = Regex("^.{12,}$")


    override fun registerUser(request: RegisterUserRequest): AuthRegisterResult {
       if (!isPasswordValid(request.password)) {
           return AuthRegisterResult.Failure(AuthRegisterError.PASSWORD_DOES_NOT_MEET_CRITERIA)
       }
        try {
            userRepository.save(request.toUserEntity())
            return AuthRegisterResult.Success
        } catch (_ : DataIntegrityViolationException) {
            return AuthRegisterResult.Failure(AuthRegisterError.EMAIL_ALREADY_REGISTERED)
        }
    }


    private fun isPasswordValid(password: String): Boolean {
        return password.matches(passwordRegex)
    }
}