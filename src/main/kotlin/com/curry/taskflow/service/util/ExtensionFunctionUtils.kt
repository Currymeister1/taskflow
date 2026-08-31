package com.curry.taskflow.service.util

import com.curry.taskflow.service.domain.enums.TaskStatus
import com.curry.taskflow.dao.entity.TaskEntity
import java.time.LocalDate

fun Set<String>.normalizeTags(): Set<String> = this.map { it.trim().lowercase() }.filter { it.isNotBlank() }.toSet()

fun TaskEntity.isOverDue(): Boolean = this.status != TaskStatus.DONE.value && this.dueDate?.isAfter(LocalDate.now()) ?: false

fun TaskEntity.isActionable(): Boolean = this.status == TaskStatus.TO_DO.value || this.status == TaskStatus.IN_PROGRESS.value

fun TaskStatus.displayName(): String = when(this) {
    TaskStatus.DONE -> "Done"
    TaskStatus.TO_DO -> "To do"
    TaskStatus.IN_PROGRESS -> "In progress"
}