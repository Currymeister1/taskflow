package com.curry.taskflow.service

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus

interface TaskService {
    fun getTasks(taskStatus: TaskStatus? = null, taskPriority: TaskPriority? = null): List<TaskResponse>
    fun create(createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse
    fun update(taskId: Long, createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse
    fun delete(taskId: Long)
    fun getTaskById(taskId: Long): TaskResponse
}