package com.rbleuse.spring.reactive.couchbase.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.couchbase.core.mapping.Document
import org.springframework.data.couchbase.core.mapping.Field
import org.springframework.data.couchbase.repository.Collection
import org.springframework.data.couchbase.repository.Scope

@Document
@Scope("dev")
@Collection("task")
data class Task(
    @field:Id
    val id: String,

    @field:Field("pid")
    val projectId: String,
    val name: String,

    @field:Field("desc")
    val description: String,

    @field:Field("ownername")
    val ownerName: String,
    val cost: Long,

    @field:Version
    val version: Long?
)
