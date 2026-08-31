package com.curry.taskflow.service.domain

import com.curry.taskflow.dao.entity.TaskEntity

sealed class TaskResult {
    data class Success(val task: TaskEntity) : TaskResult()
    data class Failure(val reason: TaskError) : TaskResult()
}

