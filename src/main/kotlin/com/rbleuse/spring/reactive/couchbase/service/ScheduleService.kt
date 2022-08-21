package com.rbleuse.spring.reactive.couchbase.service

import com.rbleuse.spring.reactive.couchbase.model.Schedule
import com.rbleuse.spring.reactive.couchbase.repository.ScheduleRepository
import org.slf4j.LoggerFactory
import org.springframework.data.couchbase.core.ReactiveCouchbaseOperations
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class ScheduleService(
    private val reactiveOperations: ReactiveCouchbaseOperations,
    private val repository: ScheduleRepository
) {

    private val logger = LoggerFactory.getLogger(ScheduleService::class.java.name)

    fun saveScheduleWithRepo(schedule: Schedule) = repository.save(schedule)

    fun findByN1ql() = repository.findByN1ql()

    fun findByN1qlCustom() = repository.findByN1qlCustom()

    @Transactional
    fun saveScheduleWithTransaction(schedule: Schedule): Mono<Schedule> {
        Exception(Thread.currentThread().name).printStackTrace()
        return reactiveOperations.insertById(Schedule::class.java).one(schedule)
    }
}
