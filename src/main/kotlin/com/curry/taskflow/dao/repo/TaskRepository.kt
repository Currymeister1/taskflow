package com.curry.taskflow.dao.repo

import com.curry.taskflow.dao.entity.TaskEntity
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<TaskEntity, Long>