package com.curry.taskflow.api.exception

class TaskNotFoundException(
    message: String,
) : RuntimeException(message)

class InvalidTaskStatusException(
    message: String,
) : RuntimeException(message)

class InvalidTaskPriorityException(
    message: String,
) : RuntimeException(message)