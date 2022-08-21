package com.rbleuse.spring.reactive.couchbase.router

import com.rbleuse.spring.reactive.couchbase.handler.PersonHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class PersonRouter(
    private val personHandler: PersonHandler
) {

    @Bean
    fun routeProjects(): RouterFunction<ServerResponse> {
        return RouterFunctions
            // person
            .route(RequestPredicates.POST("/person"), personHandler::createPerson)
    }
}
