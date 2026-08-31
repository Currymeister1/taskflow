package com.curry.taskflow.service

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.service.domain.TaskFilter
import com.curry.taskflow.service.domain.TaskResult

interface TaskService {
    fun getTasks(taskFilter: TaskFilter): List<TaskResponse>
    fun create(createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse
    fun update(taskId: Long, createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResult
    fun delete(taskId: Long)
    fun getTaskById(taskId: Long): TaskResult
}