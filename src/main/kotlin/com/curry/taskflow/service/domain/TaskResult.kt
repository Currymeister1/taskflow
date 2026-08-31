package com.curry.taskflow.service.domain

import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.service.domain.enums.TaskError

sealed class TaskResult {
    data class Success(val taskResponse: TaskResponse) : TaskResult()
    data class Failure(val reason: TaskError) : TaskResult()
}

