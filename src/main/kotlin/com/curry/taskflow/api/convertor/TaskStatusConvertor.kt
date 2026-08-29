package com.curry.taskflow.api.convertor

import com.curry.taskflow.api.enums.TaskStatus
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class TaskStatusConvertor : Converter<String, TaskStatus> {
    override fun convert(source: String): TaskStatus = TaskStatus.fromText(source)
}