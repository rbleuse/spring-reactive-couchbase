package com.rbleuse.spring.reactive.couchbase.model

import org.springframework.data.annotation.Id
import org.springframework.data.couchbase.core.mapping.Document
import org.springframework.data.couchbase.core.mapping.Field
import org.springframework.data.couchbase.repository.Collection
import org.springframework.data.couchbase.repository.Scope
import java.util.*

@Document
@Scope("dev")
@Collection("person")
data class Person(
    @field:Id
    val id: String,

    @field:Field
    val firstName: String,

    @field:Field
    val lastName: String
)