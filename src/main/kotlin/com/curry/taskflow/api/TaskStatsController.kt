package com.curry.taskflow.api

import com.curry.taskflow.service.TaskStatsService
import com.curry.taskflow.service.domain.enums.TaskStatus
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
@RequestMapping(value = ["${ApiConstants.ROOT_PATH}${ApiConstants.API_VERSION}/task/stats"], produces = ["application/json"])
class TaskStatsController(private val taskStatsService: TaskStatsService) {

    @GetMapping("/by-status")
    fun getTasksStatusStats(): ResponseEntity<Map<TaskStatus, Int>> =
        ResponseEntity.ok(taskStatsService.getTaskStatusStats())

}