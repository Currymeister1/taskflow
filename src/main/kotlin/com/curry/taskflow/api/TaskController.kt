package com.curry.taskflow.api

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.service.domain.enums.TaskPriority
import com.curry.taskflow.service.domain.enums.TaskStatus
import com.curry.taskflow.service.TaskService
import com.curry.taskflow.service.domain.TaskFilter
import com.curry.taskflow.service.domain.TaskResult
import com.curry.taskflow.service.domain.enums.SortTaskBy
import com.curry.taskflow.service.domain.enums.SortTaskOrder
import com.curry.taskflow.service.domain.enums.TaskError
import jakarta.validation.Valid
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
@RequestMapping(value = ["${ApiConstants.ROOT_PATH}${ApiConstants.API_VERSION}/task"], produces = ["application/json"])
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getTasks(
        @RequestParam("status", required = false) taskStatus: TaskStatus?,
        @RequestParam("taskPriority", required = false) taskPriority: TaskPriority?,
        @RequestParam("q", required = false) textSearch: String?,
        @RequestParam("tags", required = false) tags: Set<String>?,
        @RequestParam("sortBy", defaultValue = "CREATED_AT") sortBy: SortTaskBy,
        @RequestParam("direction", defaultValue = "DESC") direction: SortTaskOrder,
    ):
            ResponseEntity<List<TaskResponse>> =
        ResponseEntity.ok(
            taskService.getTasks(
                TaskFilter(
                    taskStatus = taskStatus,
                    taskPriority = taskPriority,
                    textSearch = textSearch,
                    taskTags = tags,
                    sortTaskBy = sortBy,
                    sortTaskOrder = direction,
                ),
            )
        )


    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskResponse> =
        when(val taskResult = taskService.getTaskById(id)) {
            is TaskResult.Success -> ResponseEntity.ok(taskResult.taskResponse)
            is TaskResult.Failure -> when(taskResult.reason) {
                TaskError.NOT_FOUND -> ResponseEntity.notFound().build()
                else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }
        }

    @PostMapping
    fun createTask(@RequestBody @Valid createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): ResponseEntity<TaskResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(createOrUpdateTaskRequest))

    @PatchMapping("/{id}")
    fun updateTask(
        @PathVariable("id") id: Long,
        @RequestBody @Valid updateTaskRequest: CreateOrUpdateTaskRequest,
    ): ResponseEntity<TaskResponse> =
        when (val taskResult = taskService.update(id, updateTaskRequest)) {
            is TaskResult.Success -> ResponseEntity.ok(taskResult.taskResponse)
            is TaskResult.Failure -> when (taskResult.reason) {
                TaskError.NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }
        }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable("id") id: Long): ResponseEntity<Any> {
        taskService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}