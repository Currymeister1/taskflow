package com.curry.taskflow.api

import com.curry.taskflow.api.dto.GetTaskResponse
import com.curry.taskflow.api.dto.Task
import com.curry.taskflow.service.TaskService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
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
    fun getTasks(): ResponseEntity<List<GetTaskResponse>> {
        val tasks = taskService.getTasks()

        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Any> {
        return taskService
            .getTaskById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    ProblemDetail
                        .forStatusAndDetail(
                            HttpStatus.NOT_FOUND,
                            "Task with id $id not found"
                        )
                )
    }

    @PostMapping
    fun createTask(@RequestBody task: Task) {

    }

    @PatchMapping("/{id}")
    fun updateTask(@PathVariable("id") id: Long, @RequestBody task: Task) {

    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable("id") id: Long) {

    }
}