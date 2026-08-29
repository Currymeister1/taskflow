package com.curry.taskflow.api.exception.mapper

import com.curry.taskflow.api.exception.TaskNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class TaskExceptionMapper {

    @ExceptionHandler
    fun handleTaskNotFoundException(ex: TaskNotFoundException):
            ResponseEntity<ErrorMessageModel> = ResponseEntity(
        ErrorMessageModel(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
        ),
        HttpStatus.NOT_FOUND
    )

}

class ErrorMessageModel(
    val status: Int? = null,
    val message: String? = null,
)


