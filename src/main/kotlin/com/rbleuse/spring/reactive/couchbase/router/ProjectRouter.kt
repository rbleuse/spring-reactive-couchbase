package com.rbleuse.spring.reactive.couchbase.router

import com.rbleuse.spring.reactive.couchbase.handler.ProjectHandler
import com.rbleuse.spring.reactive.couchbase.handler.ScheduleHandler
import com.rbleuse.spring.reactive.couchbase.handler.TaskHandler
import com.rbleuse.spring.reactive.couchbase.handler.TestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ProjectRouter(
    private val projectHandler: ProjectHandler,
    private val taskHandler: TaskHandler,
    private val scheduleHandler: ScheduleHandler,
    private val testHandler: TestHandler
) {

    @Bean
    fun routeProjects(): RouterFunction<ServerResponse> {
        return RouterFunctions
            // projects
            .route(RequestPredicates.POST("/project"), projectHandler::createProject)
            .andRoute(RequestPredicates.GET("/project"), projectHandler::findAll)
            .andRoute(RequestPredicates.GET("/project/name"), projectHandler::findByName)
            .andRoute(RequestPredicates.GET("/project/like"), projectHandler::findByNameLike)
            .andRoute(RequestPredicates.GET("/project/{id}"), projectHandler::findById)
            .andRoute(RequestPredicates.DELETE("/project/{id}"), projectHandler::deleteById)
            .andRoute(RequestPredicates.GET("/projects"), projectHandler::findAllByIds)
            .andRoute(RequestPredicates.GET("/projectsCountry"), projectHandler::findAllByCountry)
            // tasks
            .andRoute(RequestPredicates.POST("/task"), taskHandler::createTask)
            .andRoute(RequestPredicates.GET("/task"), taskHandler::findAll)
            .andRoute(RequestPredicates.GET("/task/{id}"), taskHandler::findById)
            .andRoute(RequestPredicates.DELETE("/task/{id}"), taskHandler::deleteById)

            // schedule
            .andRoute(RequestPredicates.POST("/schedule"), scheduleHandler::createSchedule)

            // test
            .andRoute(RequestPredicates.GET("/test"), testHandler::createTest)
    }
}
