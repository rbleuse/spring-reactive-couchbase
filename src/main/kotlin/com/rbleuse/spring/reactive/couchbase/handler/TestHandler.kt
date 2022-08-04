package com.rbleuse.spring.reactive.couchbase.handler

import com.rbleuse.spring.reactive.couchbase.model.Nested
import com.rbleuse.spring.reactive.couchbase.model.Schedule
import com.rbleuse.spring.reactive.couchbase.model.TestUUID
import com.rbleuse.spring.reactive.couchbase.service.ScheduleService
import com.rbleuse.spring.reactive.couchbase.service.TestUUIDService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.*

@Component
class TestHandler(
    private val service: TestUUIDService
) {

    fun createTest(serverRequest: ServerRequest): Mono<ServerResponse> {
        val test = TestUUID(UUID.randomUUID(), "TEST")

        return service.createTest(test).then(
            service.getById(test.id)
        ).flatMap {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
        }
    }
}
