package com.rbleuse.spring.reactive.couchbase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringReactiveCouchbaseApplication

fun main(args: Array<String>) {
    runApplication<SpringReactiveCouchbaseApplication>(*args)
}
