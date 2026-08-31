package com.curry.taskflow.service.domain

import com.curry.taskflow.service.domain.enums.SortTaskBy
import com.curry.taskflow.service.domain.enums.SortTaskOrder
import com.curry.taskflow.service.domain.enums.TaskPriority
import com.curry.taskflow.service.domain.enums.TaskStatus

data class TaskFilter(
    val taskStatus: TaskStatus? = null,
    val taskPriority: TaskPriority? = null,
    val textSearch: String? = null,
    val taskTags: Set<String>? = null,
    val sortTaskBy: SortTaskBy,
    val sortTaskOrder: SortTaskOrder,
)
