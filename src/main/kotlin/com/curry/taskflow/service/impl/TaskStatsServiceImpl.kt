package com.curry.taskflow.service.impl

import com.curry.taskflow.api.dto.TaskWorkloadStatResponse
import com.curry.taskflow.dao.repo.TaskRepository
import com.curry.taskflow.service.TaskStatsService
import com.curry.taskflow.service.domain.enums.TaskPriority
import com.curry.taskflow.service.domain.enums.TaskStatus
import com.curry.taskflow.service.util.weight
import org.springframework.stereotype.Service

@Service
class TaskStatsServiceImpl(private val taskRepository: TaskRepository) : TaskStatsService {
    override fun getTaskStatusStats(): Map<TaskStatus, Int> {
        val count = taskRepository
            .findAll()
            .groupingBy { TaskStatus.fromValue(it.status) }
            .eachCount()

        return TaskStatus.entries.associateWith { count[it] ?: 0 }
    }

    override fun getTaskWorkload(): TaskWorkloadStatResponse {
        val uncompletedTasks = taskRepository
            .findTaskEntitiesByStatusNot(TaskStatus.DONE.value)

        return TaskWorkloadStatResponse(
            workloadScore = uncompletedTasks.sumOf { taskEntity -> TaskPriority.fromValue(taskEntity.priority).weight() },
            activeTaskCount = uncompletedTasks.size
        )
    }
}