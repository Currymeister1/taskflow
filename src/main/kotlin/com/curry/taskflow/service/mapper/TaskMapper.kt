package com.curry.taskflow.service.mapper

import com.curry.taskflow.api.dto.CreateTaskRequest
import com.curry.taskflow.api.dto.CreateOrGetTaskResponse
import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import com.curry.taskflow.dao.entity.TaskEntity
import java.time.LocalDate

fun TaskEntity.toCreateOrGetTaskResponse(): CreateOrGetTaskResponse {
    return CreateOrGetTaskResponse(
        id = id,
        title = title,
        description = description,
        status = TaskStatus.fromValue(status),
        priority = TaskPriority.fromValue(priority),
        createdAt = createdAt,
    )
}

fun CreateTaskRequest.toTaskEntity(): TaskEntity {
    return TaskEntity(
        title = title,
        description = description,
        status = status.value,
        priority = priority.value
    )
}