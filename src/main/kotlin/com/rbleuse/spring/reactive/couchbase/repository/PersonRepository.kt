package com.rbleuse.spring.reactive.couchbase.repository

import com.rbleuse.spring.reactive.couchbase.model.Name
import com.rbleuse.spring.reactive.couchbase.model.Person
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository
import reactor.core.publisher.Flux

interface PersonRepository : ReactiveCouchbaseRepository<Person, String> {

    fun findByName(name: String): Flux<Name>
}
