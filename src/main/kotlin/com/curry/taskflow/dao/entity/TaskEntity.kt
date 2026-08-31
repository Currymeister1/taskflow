package com.curry.taskflow.dao.entity

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "tasks")
class TaskEntity (
    @Column(nullable = false)
    var title: String,

    var description: String? = null,

    @Column(nullable = false)
    var status: Int,

    @Column(nullable = false)
    var priority: Int,

    @ElementCollection
    @CollectionTable(name = "task_tags", joinColumns = [JoinColumn(name = "task_id")])
    @Column(name = "tag")
    var tags: Set<String> = mutableSetOf(),

    @Column(nullable = true)
    var dueDate: LocalDate? = null,
) : DateAudit()