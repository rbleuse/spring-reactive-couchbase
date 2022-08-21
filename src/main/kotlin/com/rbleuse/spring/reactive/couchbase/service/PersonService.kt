package com.rbleuse.spring.reactive.couchbase.service

import com.rbleuse.spring.reactive.couchbase.model.Person
import com.rbleuse.spring.reactive.couchbase.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val repository: PersonRepository
) {

    fun createPerson(person: Person) = repository.save(person)
    fun getProjectionByName(name: String) = repository.findByName(name)
}
