package com.curry.taskflow.api

import com.curry.taskflow.api.dto.CreateOrUpdateTaskRequest
import com.curry.taskflow.api.dto.TaskResponse
import com.curry.taskflow.service.TaskService
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
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
@RequestMapping(value = ["${ApiConstants.ROOT_PATH}${ApiConstants.API_VERSION}/task"], produces = ["application/json"])
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getTasks(): ResponseEntity<List<TaskResponse>> = ResponseEntity.ok(taskService.getTasks())


    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Any> =
        taskService
            .getTaskById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    mapOf(
                        "status" to "404",
                        "message" to "Task with id $id not found."
                    )
                )


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