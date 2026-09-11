package com.curry.taskflow.dao.entity

import com.curry.taskflow.service.domain.enums.UserRole
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity (
    @Column(nullable = false)
    var email: String,

    @Column(name = "hashed_password", nullable = false)
    var hashedPassword: String,

    @ElementCollection
    @CollectionTable(name = "roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "roles", nullable = false)
    var roles: Set<UserRole> = mutableSetOf(),
) : BaseEntity()

