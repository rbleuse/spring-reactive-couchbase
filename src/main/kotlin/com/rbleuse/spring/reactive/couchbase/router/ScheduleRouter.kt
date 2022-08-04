package com.rbleuse.spring.reactive.couchbase.router

import com.rbleuse.spring.reactive.couchbase.handler.ScheduleHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ScheduleRouter(
    private val scheduleHandler: ScheduleHandler
) {

    @Bean
    fun routeProjects(): RouterFunction<ServerResponse> {
        return RouterFunctions
            // schedule
            .route(RequestPredicates.POST("/schedule"), scheduleHandler::createSchedule)
    }
}
