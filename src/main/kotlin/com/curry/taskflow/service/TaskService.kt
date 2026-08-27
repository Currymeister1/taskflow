package com.curry.taskflow.service

import com.curry.taskflow.api.dto.CreateTaskRequest
import com.curry.taskflow.api.dto.CreateOrGetTaskResponse
import com.curry.taskflow.api.dto.Task

interface TaskService {
    fun getTasks(): List<CreateOrGetTaskResponse>
    fun create(createTaskRequest: CreateTaskRequest): CreateOrGetTaskResponse
    fun update(taskId: Long): Task
    fun delete(taskId: Long)
    fun getTaskById(taskId: Long): CreateOrGetTaskResponse?
}