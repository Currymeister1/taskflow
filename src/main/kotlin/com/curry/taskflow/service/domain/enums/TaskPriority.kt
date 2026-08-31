package com.curry.taskflow.service.domain.enums

import com.curry.taskflow.api.exception.InvalidTaskPriorityException

enum class TaskPriority(val value: Int) {
    LOW(0),
    MEDIUM(1),
    HIGH(2),
    URGENT(3);


    companion object {
        fun fromValue(value: Int): TaskPriority =
            entries.find { it.value == value } ?: throw IllegalArgumentException("TaskPriority $value not found")

        fun fromText(text: String): TaskPriority =
            entries.find { it.name.compareTo(text, true) == 0 } ?:
            throw InvalidTaskPriorityException("TaskPriority $text not found")
    }
}