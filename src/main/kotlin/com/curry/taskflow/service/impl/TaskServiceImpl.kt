package com.curry.taskflow.service.impl

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.api.exception.TaskNotFoundException
import com.curry.taskflow.dao.entity.TaskEntity
import com.curry.taskflow.dao.repo.TaskRepository
import com.curry.taskflow.service.TaskService
import com.curry.taskflow.service.mapper.toTaskEntity
import com.curry.taskflow.service.mapper.toTaskResponse
import com.curry.taskflow.service.modal.TaskFilter
import com.curry.taskflow.service.util.normalizeTags
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(private val taskRepository: TaskRepository) : TaskService {
    override fun getTasks(taskFilter: TaskFilter): List<TaskResponse> {
        val predicates = getPredicates(taskFilter)

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

    private fun getPredicates(taskFilter: TaskFilter): List<(TaskEntity) -> Boolean> =
        listOfNotNull(
            taskFilter.taskStatus?.let { s -> { task: TaskEntity -> task.status == s.value } },
            taskFilter.taskPriority?.let { p -> { task: TaskEntity -> task.priority == p.value } },
            taskFilter.textSearch?.let { ts ->
                { task: TaskEntity ->
                    task.matchQuery(ts)
                }
            },
            taskFilter.taskTags?.let { tags ->
                val normalizedRequestTags = tags.normalizeTags()
                return@let { task: TaskEntity ->
                    normalizedRequestTags.isEmpty() ||
                            task.tags.normalizeTags().intersect(normalizedRequestTags).isNotEmpty()
                }
            }
        )

    private fun TaskEntity.matchQuery(s1: String): Boolean =
        this.title.containIgnoreCase(s1) || this.description.containIgnoreCase(s1)

    private fun String?.containIgnoreCase(s1: String) = this?.contains(s1, ignoreCase = true) ?: false
}