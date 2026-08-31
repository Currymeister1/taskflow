package com.curry.taskflow.api.exception.mapper

import com.curry.taskflow.api.exception.InvalidTaskPriorityException
import com.curry.taskflow.api.exception.InvalidTaskStatusException
import com.curry.taskflow.api.exception.TaskNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class TaskExceptionMapper {

    @ExceptionHandler(TaskNotFoundException::class)
    fun handleTaskNotFoundException(ex: TaskNotFoundException):
            ResponseEntity<ErrorMessageModel> = ResponseEntity(
        ErrorMessageModel(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
        ),
        HttpStatus.NOT_FOUND
    )

    @ExceptionHandler(InvalidTaskStatusException::class)
    fun handleInvalidTaskStatusException(ex: InvalidTaskStatusException):
            ResponseEntity<ErrorMessageModel> = ResponseEntity(
        ErrorMessageModel(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message,
        ),
        HttpStatus.BAD_REQUEST
    )

    @ExceptionHandler(InvalidTaskPriorityException::class)
    fun handleInvalidTaskPriorityException(ex: InvalidTaskPriorityException):
            ResponseEntity<ErrorMessageModel> = ResponseEntity(
        ErrorMessageModel(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message
        ),
        HttpStatus.BAD_REQUEST
    )

    @ExceptionHandler(RuntimeException::class)
    fun handleRunTimeException(ex: RuntimeException):
            ResponseEntity<ErrorMessageModel> = ResponseEntity(
        ErrorMessageModel(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "Something went wrong!",
        ), HttpStatus.INTERNAL_SERVER_ERROR
    )
        .also { ex.printStackTrace() }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException):
            ResponseEntity<ErrorMessageModel> = ResponseEntity(
        ErrorMessageModel(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Validation Failed",
            errors = ex.bindingResult.fieldErrors
                .associate {
                    it.field to (it.defaultMessage ?: "Invalid value")
                }
        ), HttpStatus.BAD_REQUEST
    )
}

class ErrorMessageModel(
    val status: Int? = null,
    val message: String? = null,
    val errors: Map<String, String>? = null,
)


