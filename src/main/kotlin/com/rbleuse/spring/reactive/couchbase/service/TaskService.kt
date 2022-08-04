package com.rbleuse.spring.reactive.couchbase.service

import com.rbleuse.spring.reactive.couchbase.model.Task
import com.rbleuse.spring.reactive.couchbase.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val repository: TaskRepository
) {

    fun createTask(task: Task) = repository.save(task)

    fun findById(id: String) = repository.findById(id)

    fun findAll() = repository.findAll()

    fun deleteById(id: String) = repository.deleteById(id)
}
