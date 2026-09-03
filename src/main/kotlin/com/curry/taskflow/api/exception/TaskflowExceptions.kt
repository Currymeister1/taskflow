package com.curry.taskflow.api.exception

@Deprecated("Switched to sealed class based result")
class TaskNotFoundException(
    message: String,
) : RuntimeException(message)

class InvalidTaskStatusException(
    message: String,
) : RuntimeException(message)

class InvalidTaskPriorityException(
    message: String,
) : RuntimeException(message)