package com.curry.taskflow.service.impl

import com.curry.taskflow.api.dto.CreateTaskRequest
import com.curry.taskflow.api.dto.CreateOrGetTaskResponse
import com.curry.taskflow.api.dto.Task
import com.curry.taskflow.dao.repo.TaskRepository
import com.curry.taskflow.service.TaskService
import com.curry.taskflow.service.mapper.toCreateOrGetTaskResponse
import com.curry.taskflow.service.mapper.toTaskEntity
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(private val taskRepository: TaskRepository) : TaskService {
    override fun getTasks(): List<CreateOrGetTaskResponse> = taskRepository
        .findAll()
        .map { task -> task.toCreateOrGetTaskResponse() }

    override fun create(createTaskRequest: CreateTaskRequest): CreateOrGetTaskResponse {
        val taskEntity = createTaskRequest.toTaskEntity()

        taskRepository.save(taskEntity)

        return taskEntity.toCreateOrGetTaskResponse()
    }

    override fun update(taskId: Long): Task {
        TODO("Not yet implemented")
    }

    override fun delete(taskId: Long) {
        taskRepository.deleteById(taskId)
    }

    override fun getTaskById(taskId: Long): CreateOrGetTaskResponse? = taskRepository
        .findById(taskId)
        .map { task -> task.toCreateOrGetTaskResponse() }
        .orElse(null)
}