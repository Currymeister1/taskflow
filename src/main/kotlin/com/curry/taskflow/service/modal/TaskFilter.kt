package com.curry.taskflow.service.modal

import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus

data class TaskFilter(
    val taskStatus: TaskStatus? = null,
    val taskPriority: TaskPriority? = null,
    val textSearch: String? = null,
    val taskTags: Set<String>? = null,
)
