package com.curry.taskflow.service.util

fun Set<String>.normalizeTags(): Set<String> = this.map { it.trim().lowercase() }.filter { it.isNotBlank() }.toSet()


