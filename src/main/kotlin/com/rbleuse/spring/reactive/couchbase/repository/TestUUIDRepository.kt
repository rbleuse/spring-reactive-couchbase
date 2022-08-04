package com.rbleuse.spring.reactive.couchbase.repository

import com.rbleuse.spring.reactive.couchbase.model.TestUUID
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository
import java.util.*

interface TestUUIDRepository : ReactiveCouchbaseRepository<TestUUID, UUID>
