package com.rbleuse.spring.reactive.couchbase.repository

import com.rbleuse.spring.reactive.couchbase.model.Schedule
import org.springframework.data.couchbase.repository.Query
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository
import reactor.core.publisher.Flux

interface ScheduleRepository : ReactiveCouchbaseRepository<Schedule, String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    fun findByN1ql(): Flux<Schedule>

    @Query("SELECT c.* FROM #{#n1ql.bucket} c WHERE #{#n1ql.filter}")
    fun findByN1qlCustom(): Flux<Schedule>
}
