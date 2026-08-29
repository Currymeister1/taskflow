package com.curry.taskflow.service

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse

interface TaskService {
    fun getTasks(): List<TaskResponse>
    fun create(createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse
    fun update(taskId: Long, createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse
    fun delete(taskId: Long)
    fun getTaskById(taskId: Long): TaskResponse
}