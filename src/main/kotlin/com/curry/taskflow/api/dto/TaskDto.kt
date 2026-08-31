package com.curry.taskflow.api.dto

import com.curry.taskflow.service.domain.enums.TaskPriority
import com.curry.taskflow.service.domain.enums.TaskStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateOrUpdateTaskRequest(
    @field:NotBlank(message = "Title must not be blank2")
    @field:Size(min = 1, max = 255, message = "Title must not be more than 255 characters")
    val title: String,
    val description: String? = null,
    val status: TaskStatus = TaskStatus.TO_DO,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val tags: Set<String>? = null,
)

data class TaskResponse(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val status: TaskStatus,
    val priority: TaskPriority,
    val createdAt: LocalDate,
    val tags: Set<String>? = null,
)