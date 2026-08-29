package com.curry.taskflow.api.convertor

import com.curry.taskflow.api.enums.TaskPriority
import com.curry.taskflow.api.enums.TaskStatus
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class TaskStatusConverter : Converter<String, TaskStatus> {
    override fun convert(source: String): TaskStatus = TaskStatus.fromText(source)
}

@Component
class TaskPriorityConverter : Converter<String, TaskPriority> {
    override fun convert(source: String): TaskPriority = TaskPriority.fromText(source)
}