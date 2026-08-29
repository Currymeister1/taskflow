package com.curry.taskflow.api.enums

import com.curry.taskflow.api.exception.InvalidTaskStatusException
import com.fasterxml.jackson.annotation.JsonCreator

enum class TaskStatus(val value: Int) {
    TO_DO(0),
    PROGRESS(1),
    DONE(2);

    fun next(): TaskStatus? = when (this) {
        TO_DO -> TaskStatus.PROGRESS
        PROGRESS -> TaskStatus.DONE
        DONE -> null
    }


    companion object {
        fun fromValue(value: Int): TaskStatus = entries.find { it.value == value }
            ?: throw IllegalArgumentException("TaskStatus with value $value not found")

        @JsonCreator
        fun fromText(text: String): TaskStatus = entries.find { it.name.equals(text, ignoreCase = true) }
        ?: throw InvalidTaskStatusException("Invalid task status. Only these statuses are allowed: ${entries.map { it.name }}")
    }
}