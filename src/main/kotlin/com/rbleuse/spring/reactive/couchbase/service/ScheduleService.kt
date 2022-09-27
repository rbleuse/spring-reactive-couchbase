package com.rbleuse.spring.reactive.couchbase.service

import com.rbleuse.spring.reactive.couchbase.model.Schedule
import com.rbleuse.spring.reactive.couchbase.repository.ScheduleRepository
import org.springframework.data.couchbase.core.ReactiveCouchbaseOperations
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class ScheduleService(
    private val reactiveOperations: ReactiveCouchbaseOperations,
    private val repository: ScheduleRepository
) {
    fun saveScheduleWithRepo(schedule: Schedule) = repository.save(schedule)

    @Transactional
    fun saveScheduleWithTransaction(schedule: Schedule): Mono<Schedule> {
        return reactiveOperations.insertById(Schedule::class.java).one(schedule)
    }
}
