package com.rbleuse.spring.reactive.couchbase.repository

import com.rbleuse.spring.reactive.couchbase.model.Task
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository

interface TaskRepository : ReactiveCouchbaseRepository<Task, String>
