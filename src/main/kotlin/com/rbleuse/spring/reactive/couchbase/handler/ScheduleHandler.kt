package com.rbleuse.spring.reactive.couchbase.handler

import com.rbleuse.spring.reactive.couchbase.model.Nested
import com.rbleuse.spring.reactive.couchbase.model.Schedule
import com.rbleuse.spring.reactive.couchbase.service.ScheduleService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ScheduleHandler(
    private val service: ScheduleService
) {

    fun createSchedule(serverRequest: ServerRequest): Mono<ServerResponse> {
        val schedule = Schedule("1", Nested(true), listOf("TEST").toTypedArray())

        return service.saveScheduleWithTransaction(schedule.copy(id = "2"))
            .flatMap {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }

        /**return service.saveScheduleWithRepo(schedule)
         .flatMap {
         service.saveScheduleWithTransaction(schedule.copy(id = "2"))
         }
         .flatMap {
         ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
         }*/
    }
}
