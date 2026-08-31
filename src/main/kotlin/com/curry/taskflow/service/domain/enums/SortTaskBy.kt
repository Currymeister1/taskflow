package com.curry.taskflow.service.domain.enums

import com.curry.taskflow.dao.entity.TaskEntity
import com.curry.taskflow.service.util.weight

enum class SortTaskBy(val comparator: Comparator<TaskEntity>) {
    PRIORITY(compareBy<TaskEntity> { TaskPriority.fromValue(it.priority).weight() }.thenBy(TaskEntity::title)),
    CREATED_AT(compareBy(TaskEntity::createdAt).thenBy(TaskEntity::title)),
    TITLE(compareBy(TaskEntity::title)),
    ;
}