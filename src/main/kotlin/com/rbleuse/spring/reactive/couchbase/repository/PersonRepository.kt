package com.rbleuse.spring.reactive.couchbase.repository

import com.rbleuse.spring.reactive.couchbase.model.Person
import com.rbleuse.spring.reactive.couchbase.model.PersonName
import org.springframework.data.couchbase.repository.Query
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository
import reactor.core.publisher.Flux

interface PersonRepository : ReactiveCouchbaseRepository<Person, String> {

    @Query("select p.firstName, p.lastName, '' as __id from #{#n1ql.bucket} p WHERE p.firstName = $1")
    fun findByFirstName(firstName: String): Flux<PersonName>
}