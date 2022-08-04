package com.rbleuse.spring.reactive.couchbase.handler

import com.rbleuse.spring.reactive.couchbase.model.Project
import com.rbleuse.spring.reactive.couchbase.service.ProjectService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class ProjectHandler(
    private val service: ProjectService
) {

    fun createProject(serverRequest: ServerRequest): Mono<ServerResponse> {
        val project = serverRequest.bodyToMono<Project>()

        return project.flatMap {
            service.createProject(it)
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

    fun findByName(serverRequest: ServerRequest): Mono<ServerResponse> {
        val name = serverRequest.queryParam("name")
        return service.findByName(name.get())
            .onErrorResume {
                println(it)
                Mono.error(it)
            }
            .collectList()
            .flatMap {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }
    }

    fun findByNameLike(serverRequest: ServerRequest): Mono<ServerResponse> {
        val name = serverRequest.queryParam("name")
        return service.findByNameLike(name.get())
            .onErrorResume {
                println(it)
                Mono.error(it)
            }
            .collectList()
            .flatMap {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }
    }

    fun findAllByIds(serverRequest: ServerRequest): Mono<ServerResponse> {
        val ids = serverRequest.queryParam("ids").get().split(",")

        return service.findAllByIds(ids).collectList()
            .flatMap {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }
    }

    fun findAllByCountry(serverRequest: ServerRequest): Mono<ServerResponse> {
        val country = serverRequest.queryParam("country")

        return service.findByCountry(country.get()).collectList()
            .flatMap {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it)
            }
    }
}
