package com.rbleuse.spring.reactive.couchbase.handler

import com.rbleuse.spring.reactive.couchbase.model.Person
import com.rbleuse.spring.reactive.couchbase.service.PersonService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class PersonHandler(
    private val service: PersonService
) {

    fun createPerson(serverRequest: ServerRequest): Mono<ServerResponse> {
        val name = "test"
        val test = Person("hello", name)

        return service.createPerson(test)
            .then(
                service.getProjectionByName(name).collectList()
            )
            .flatMap {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }
    }
}
