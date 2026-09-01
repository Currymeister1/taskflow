package com.curry.taskflow.api.dto

data class PagedResponse<T>(
    val items: List<T>,
    val page: Int,
    val size: Int,
    val totalElements: Int,
)
