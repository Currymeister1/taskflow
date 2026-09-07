package com.curry.taskflow.api.dto

data class TaskWorkloadStatResponse(
    val workloadScore: Int,
    val activeTaskCount: Int,
    val overDueTasksCount: Int,
)