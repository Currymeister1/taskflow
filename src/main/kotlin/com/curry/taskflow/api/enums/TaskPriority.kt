package com.curry.taskflow.api.enums

enum class TaskPriority(val value: Int) {
    LOW(0),
    MEDIUM(1),
    HIGH(2),
    URGENT(3);


    companion object {
        fun fromValue(value: Int): TaskPriority =
            values().first { it.value == value } ?: throw IllegalArgumentException("TaskPriority $value not found")
    }
}