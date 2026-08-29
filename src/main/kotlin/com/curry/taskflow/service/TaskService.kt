package com.curry.taskflow.service

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import com.curry.taskflow.service.modal.TaskFilterPredicate

interface TaskService {
    fun getTasks(taskFilterPredicate: TaskFilterPredicate): List<TaskResponse>
    fun create(createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse
    fun update(taskId: Long, createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse
    fun delete(taskId: Long)
    fun getTaskById(taskId: Long): TaskResponse
}