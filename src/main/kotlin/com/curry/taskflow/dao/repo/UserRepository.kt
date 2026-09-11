package com.curry.taskflow.dao.repo

import com.curry.taskflow.dao.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
}