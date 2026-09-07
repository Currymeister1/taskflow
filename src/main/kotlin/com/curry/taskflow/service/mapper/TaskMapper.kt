package com.curry.taskflow.service.mapper

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.service.domain.enums.TaskPriority
import com.curry.taskflow.service.domain.enums.TaskStatus
import com.curry.taskflow.dao.entity.TaskEntity
import com.curry.taskflow.service.util.displayName
import com.curry.taskflow.service.util.isActionable
import com.curry.taskflow.service.util.normalizeTags

fun TaskEntity.toTaskResponse(): TaskResponse {
    return TaskResponse(
        id = id,
        title = title,
        description = description,
        status = TaskStatus.fromValue(status).displayName(),
        priority = TaskPriority.fromValue(priority).displayName(),
        createdAt = createdAt,
        tags = tags,
        dueDate = dueDate,
        isActionable = isActionable(),
    )
}

fun CreateOrUpdateTaskRequest.toTaskEntity(): TaskEntity {
    return TaskEntity(
        title = title,
        description = description,
        status = status.value,
        priority = priority.value,
        tags = tags?.normalizeTags() ?: emptySet(),
        dueDate = dueDate,
    )
}