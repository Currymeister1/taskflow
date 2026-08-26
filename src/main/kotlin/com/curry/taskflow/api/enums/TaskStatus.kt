package com.curry.taskflow.api.enums

enum class TaskStatus(val value: Int) {
    TO_DO(1),
    PROGRESS(2),
    DONE(3);

    fun next(): TaskStatus? = when (this) {
        TO_DO -> TaskStatus.PROGRESS
        PROGRESS -> TaskStatus.DONE
        DONE -> null
    }
}