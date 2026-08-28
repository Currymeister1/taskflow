package com.curry.taskflow.dao.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import java.time.LocalDate

@MappedSuperclass
abstract class DateAudit (
) : BaseEntity() {
    @Column(name = "created_at", nullable = false, updatable = false)
    lateinit var createdAt: LocalDate

    @PrePersist
    fun onCreate() {
        createdAt = LocalDate.now()
    }
}