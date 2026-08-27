package com.curry.taskflow.api.enums

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

    }
}