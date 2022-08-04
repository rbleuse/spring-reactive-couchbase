package com.rbleuse.spring.reactive.couchbase.model

import org.springframework.data.annotation.Id
import org.springframework.data.couchbase.core.mapping.Document
import org.springframework.data.couchbase.core.mapping.Field
import org.springframework.data.couchbase.repository.Collection
import org.springframework.data.couchbase.repository.Scope

@Document
@Scope("dev")
@Collection("schedule")
data class Schedule(
    @field:Id
    val id: String,

    @field:Field
    val nested: Nested,

    @field:Field
    val list: List<String>
)

data class Nested(
    @field:Field("isFree")
    val free: Boolean
)
