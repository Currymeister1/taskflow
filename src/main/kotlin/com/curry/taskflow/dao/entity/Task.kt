package com.curry.taskflow.dao.entity

import java.time.LocalDate

data class Task(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val status: TaskStatus,
    val priority: TaskPriority,
    val createdAt: LocalDate,
)

enum class TaskStatus {
    TO_DO,
    PROGRESS,
    DONE,
}

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH,
}
