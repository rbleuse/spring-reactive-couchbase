package com.rbleuse.spring.reactive.couchbase.service

import com.rbleuse.spring.reactive.couchbase.model.TestUUID
import com.rbleuse.spring.reactive.couchbase.repository.TestUUIDRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class TestUUIDService(
    private val repository: TestUUIDRepository
) {

    fun createTest(test: TestUUID) = repository.save(test)
    fun getById(id: UUID): Mono<TestUUID> = repository.findById(id)
}
