package com.curry.taskflow.service.impl

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import com.curry.taskflow.api.exception.TaskNotFoundException
import com.curry.taskflow.dao.entity.TaskEntity
import com.curry.taskflow.dao.repo.TaskRepository
import com.curry.taskflow.service.TaskService
import com.curry.taskflow.service.mapper.toTaskResponse
import com.curry.taskflow.service.mapper.toTaskEntity
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(private val taskRepository: TaskRepository) : TaskService {
    override fun getTasks(taskStatus: TaskStatus?, taskPriority: TaskPriority?): List<TaskResponse> {
        val predicates = getPredicates(taskStatus, taskPriority)

        return taskRepository
            .findAll()
            .filter { task -> predicates.all { p -> p(task) } }
            .map { task -> task.toTaskResponse() }
    }

    override fun create(createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse =
        taskRepository
            .save(createOrUpdateTaskRequest.toTaskEntity())
            .toTaskResponse()

    override fun update(taskId: Long, createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): TaskResponse {
        val task = fetchTaskById(taskId)
            ?: throw TaskNotFoundException("Task with id $taskId not found")

        task.title = createOrUpdateTaskRequest.title
        task.description = createOrUpdateTaskRequest.description
        task.status = createOrUpdateTaskRequest.status.value
        task.priority = createOrUpdateTaskRequest.priority.value

        return taskRepository.save(task).toTaskResponse()
    }

    override fun delete(taskId: Long) = taskRepository.deleteById(taskId)

    override fun getTaskById(taskId: Long): TaskResponse =
        fetchTaskById(taskId)?.toTaskResponse() ?: throw TaskNotFoundException("Task with id $taskId not found")

    private fun fetchTaskById(taskId: Long): TaskEntity? = taskRepository.findById(taskId).orElse(null)

    private fun getPredicates(
        taskStatus: TaskStatus?,
        taskPriority: TaskPriority?,
    ): List<(TaskEntity) -> Boolean> =
        listOfNotNull(
            taskStatus?.let { s -> { task: TaskEntity -> task.status == s.value } },
            taskPriority?.let { p -> { task: TaskEntity -> task.priority == p.value } },
        )
}