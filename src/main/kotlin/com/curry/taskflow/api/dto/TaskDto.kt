package com.curry.taskflow.api.dto

import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import java.time.LocalDate

data class CreateTaskRequest(
    val title: String,
    val description: String? = null,
    val status: TaskStatus = TaskStatus.TO_DO,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val createdAt: LocalDate,
)

data class GetTaskResponse(
    val id: Long,
    val title: String,
    val description: String? = null,
    val status: TaskStatus = TaskStatus.TO_DO,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val createdAt: LocalDate,
)