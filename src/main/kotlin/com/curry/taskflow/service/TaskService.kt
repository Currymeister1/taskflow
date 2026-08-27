package com.curry.taskflow.service

import com.curry.taskflow.api.dto.GetTaskResponse
import com.curry.taskflow.api.dto.Task

interface TaskService {
    fun getTasks(): List<GetTaskResponse>
    fun create(task: Task): Task
    fun update(taskId: Long): Task
    fun delete(taskId: Long)
    fun getTaskById(taskId: Long): GetTaskResponse?
}