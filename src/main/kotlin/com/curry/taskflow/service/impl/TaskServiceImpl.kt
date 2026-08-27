package com.curry.taskflow.service.impl

import com.curry.taskflow.api.dto.GetTaskResponse
import com.curry.taskflow.api.dto.Task
import com.curry.taskflow.dao.repo.TaskRepository
import com.curry.taskflow.service.TaskService
import com.curry.taskflow.service.mapper.toTask
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(private val taskRepository: TaskRepository) : TaskService {
    override fun getTasks(): List<GetTaskResponse> = taskRepository
        .findAll()
        .map { task -> task.toTask() }

    override fun create(task: Task): Task {
        TODO("Not yet implemented")
    }

    override fun update(taskId: Long): Task {
        TODO("Not yet implemented")
    }

    override fun delete(taskId: Long) {
        taskRepository.deleteById(taskId)
    }

    override fun getTaskById(taskId: Long): GetTaskResponse? = taskRepository
        .findById(taskId)
        .map { task -> task.toTask() }
        .orElse(null)
}