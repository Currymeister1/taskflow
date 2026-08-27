package com.curry.taskflow.api

import com.curry.taskflow.api.dto.CreateOrGetTaskResponse
import com.curry.taskflow.api.dto.CreateTaskRequest
import com.curry.taskflow.api.dto.Task
import com.curry.taskflow.service.TaskService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@SpringBootApplication
@RequestMapping(value = ["${ApiConstants.ROOT_PATH}${ApiConstants.API_VERSION}/task"], produces = ["application/json"])
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getTasks(): ResponseEntity<List<CreateOrGetTaskResponse>> = ResponseEntity.ok(taskService.getTasks())


    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Any> {
        return taskService
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
    }

    @PostMapping
    fun createTask(@RequestBody createTaskRequest: CreateTaskRequest): ResponseEntity<CreateOrGetTaskResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(createTaskRequest))

    @PatchMapping("/{id}")
    fun updateTask(@PathVariable("id") id: Long, @RequestBody task: Task) {

    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable("id") id: Long): ResponseEntity<Any> {
        taskService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}