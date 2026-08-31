package com.curry.taskflow.service.domain.enums

import com.curry.taskflow.dao.entity.TaskEntity

enum class SortTaskOrder(val order: (List<TaskEntity>) -> List<TaskEntity>) {
    ASC({ it}),
    DESC({ it.reversed() }),;
}