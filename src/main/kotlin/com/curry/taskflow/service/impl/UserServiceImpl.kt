package com.curry.taskflow.service.impl

import com.curry.taskflow.api.dto.RegisterUserRequest
import com.curry.taskflow.dao.repo.UserRepository
import com.curry.taskflow.service.UserService
import com.curry.taskflow.service.domain.result.AuthRegisterError
import com.curry.taskflow.service.domain.result.AuthRegisterResult
import com.curry.taskflow.service.mapper.toUserEntity
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    private val passwordRegex = Regex("^.{12,}$")


    override fun registerUser(request: RegisterUserRequest): AuthRegisterResult {
        if (!isPasswordValid(request.password)) {
            return AuthRegisterResult.Failure(AuthRegisterError.PASSWORD_DOES_NOT_MEET_CRITERIA)
        }
        try {
            userRepository.save(request.toUserEntity())
            return AuthRegisterResult.Success
        } catch (e: DataIntegrityViolationException) {
           val isDuplicateEmail = generateSequence(e as Throwable?) {it.cause}
               .filterIsInstance<ConstraintViolationException>()
               .any {it.constraintName == "users_email_key"}

            if (isDuplicateEmail) return AuthRegisterResult.Failure(AuthRegisterError.EMAIL_ALREADY_REGISTERED)
            else throw e
        }
    }


    private fun isPasswordValid(password: String): Boolean {
        return password.matches(passwordRegex)
    }
}