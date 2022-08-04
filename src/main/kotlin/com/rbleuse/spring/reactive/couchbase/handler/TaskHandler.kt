package com.rbleuse.spring.reactive.couchbase.handler

import com.rbleuse.spring.reactive.couchbase.model.Task
import com.rbleuse.spring.reactive.couchbase.service.TaskService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class TaskHandler(
    private val service: TaskService
) {

    fun createTask(serverRequest: ServerRequest): Mono<ServerResponse> {
        val task = serverRequest.bodyToMono<Task>()

        return task.flatMap {
            service.createTask(it)
        }.flatMap {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
        }.onErrorResume {
            if (it is DataIntegrityViolationException) {
                ServerResponse.badRequest().build()
            } else {
                ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build()
            }
        }
    }

    fun findById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")

        return service.findById(id).flatMap {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
        }.switchIfEmpty(ServerResponse.notFound().build())
    }

    fun findAll(serverRequest: ServerRequest): Mono<ServerResponse> {
        return service.findAll().collectList()
            .flatMap {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }
    }

    fun deleteById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")

        return service.deleteById(id).flatMap {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
        }
    }
}
