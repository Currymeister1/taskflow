package com.curry.taskflow.service

import com.curry.taskflow.api.dto.Task

interface TaskService {
    fun getTasks(): List<Task>
    fun create(task: Task): Task
    fun update(taskId: Long): Task
    fun delete(taskId: Long)
    fun getTaskById(taskId: String): Task?
}