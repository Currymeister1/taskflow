package com.curry.taskflow.api.dto

import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import java.time.LocalDate

data class Task(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val status: TaskStatus = TaskStatus.TO_DO,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val createdAt: LocalDate,
)
