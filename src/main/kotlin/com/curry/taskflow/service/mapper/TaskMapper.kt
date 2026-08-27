package com.curry.taskflow.service.mapper

import com.curry.taskflow.api.dto.GetTaskResponse
import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import com.curry.taskflow.dao.entity.TaskEntity

fun TaskEntity.toTask(): GetTaskResponse {
    return GetTaskResponse(
        id = id,
        title = title,
        description = description,
        status = TaskStatus.fromValue(status),
        priority = TaskPriority.fromValue(priority),
        createdAt = createdAt,
    )
}