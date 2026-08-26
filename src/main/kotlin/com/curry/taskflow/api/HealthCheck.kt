package com.curry.taskflow.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
@RequestMapping("${ApiConstants.ROOT_PATH}/${ApiConstants.API_VERSION}")
class HealthCheck {

    @GetMapping("/health")
    fun health() = mapOf("status" to "UP")
}