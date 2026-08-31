package com.curry.taskflow.service

import com.curry.taskflow.service.domain.enums.TaskStatus

interface TaskStatsService {

    fun getTaskStatusStats(): Map<TaskStatus, Int>
}