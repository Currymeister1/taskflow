package com.curry.taskflow.api

import com.curry.taskflow.api.dto.Task
import com.curry.taskflow.service.TaskService
import org.springframework.boot.autoconfigure.SpringBootApplication
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
@RequestMapping(value = ["${ApiConstants.ROOT_PATH}/${ApiConstants.API_VERSION}/task"], produces = ["application/json"])
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getTasks() {
        ApiConstants.API_VERSION
        taskService.getTasks()
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        return ResponseEntity.ok().build()
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