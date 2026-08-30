package com.curry.taskflow.api

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import com.curry.taskflow.service.TaskService
import com.curry.taskflow.service.modal.TaskFilter
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
    ):
            ResponseEntity<List<TaskResponse>> =
        ResponseEntity.ok(
            taskService.getTasks(
                TaskFilter(
                    taskStatus = taskStatus,
                    taskPriority = taskPriority,
                    textSearch = textSearch,
                    taskTags = tags,
                ),
            )
        )


    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Any> = ResponseEntity.ok(taskService.getTaskById(id))

    @PostMapping
    fun createTask(@RequestBody createOrUpdateTaskRequest: CreateOrUpdateTaskRequest): ResponseEntity<TaskResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(createOrUpdateTaskRequest))

    @PatchMapping("/{id}")
    fun updateTask(
        @PathVariable("id") id: Long,
        @RequestBody updateTaskRequest: CreateOrUpdateTaskRequest,
    ): ResponseEntity<TaskResponse> =
        ResponseEntity.status(HttpStatus.OK).body(taskService.update(id, updateTaskRequest))

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable("id") id: Long): ResponseEntity<Any> {
        taskService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}