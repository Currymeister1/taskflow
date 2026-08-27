package com.curry.taskflow.api.dto

import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateTaskRequest(
    @field:Size(min = 1, max = 255, message = "Title must not be more than 255 characters")
    val title: String,
    val description: String? = null,
    val status: TaskStatus = TaskStatus.TO_DO,
    val priority: TaskPriority = TaskPriority.MEDIUM,
)

data class CreateOrGetTaskResponse(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val status: TaskStatus,
    val priority: TaskPriority,
    val createdAt: LocalDate,
)