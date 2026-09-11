package com.curry.taskflow.service.domain.result

sealed class AuthRegisterResult {
    object Success : AuthRegisterResult()
    data class Failure(val authRegisterError : AuthRegisterError) : AuthRegisterResult()
}

enum class AuthRegisterError (val errorDescription: String){
    EMAIL_ALREADY_REGISTERED("Email already exists"),
    PASSWORD_DOES_NOT_MEET_CRITERIA("Password is less than 12 characters"),
}