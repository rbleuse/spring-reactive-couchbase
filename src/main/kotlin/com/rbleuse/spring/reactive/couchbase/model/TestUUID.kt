package com.rbleuse.spring.reactive.couchbase.model

import org.springframework.data.annotation.Id
import org.springframework.data.couchbase.core.mapping.Document
import org.springframework.data.couchbase.core.mapping.Field
import org.springframework.data.couchbase.repository.Collection
import org.springframework.data.couchbase.repository.Scope
import java.util.*

@Document
@Scope("dev")
@Collection("schedule")
data class TestUUID(
    @field:Id
    val id: UUID,

    @field:Field
    val test: String
)
