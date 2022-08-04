package com.rbleuse.spring.reactive.couchbase.repository

import com.rbleuse.spring.reactive.couchbase.model.Schedule
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository

interface ScheduleRepository : ReactiveCouchbaseRepository<Schedule, String>
