package com.curry.taskflow.service.domain.enums

import com.curry.taskflow.dao.entity.TaskEntity

enum class SortTaskBy(val comparator: Comparator<TaskEntity>) {
    PRIORITY(compareBy(TaskEntity::priority).thenBy(TaskEntity::title)),
    CREATED_AT(compareBy(TaskEntity::createdAt).thenBy(TaskEntity::title)),
    TITLE(compareBy(TaskEntity::title)),;
}