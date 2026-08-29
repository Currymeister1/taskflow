package com.curry.taskflow.service.mapper

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import com.curry.taskflow.dao.entity.TaskEntity

fun TaskEntity.toTaskResponse(): TaskResponse {
    return TaskResponse(
        id = id,
        title = title,
        description = description,
        status = TaskStatus.fromValue(status),
        priority = TaskPriority.fromValue(priority),
        createdAt = createdAt,
    )
}

fun CreateOrUpdateTaskRequest.toTaskEntity(): TaskEntity {
    return TaskEntity(
        title = title,
        description = description,
        status = status.value,
        priority = priority.value,
    )
}