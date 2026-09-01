package com.curry.taskflow.dao.repo

import com.curry.taskflow.dao.entity.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<TaskEntity, Long> {
    fun findTaskEntitiesByStatusNot(status: Int): MutableList<TaskEntity>
}