package com.rbleuse.spring.reactive.couchbase.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.couchbase.core.mapping.Document
import org.springframework.data.couchbase.core.mapping.Field
import org.springframework.data.couchbase.repository.Collection
import org.springframework.data.couchbase.repository.Scope

@Document
@Scope("dev")
@Collection("project")
data class Project(
    @field:Id
    val id: String,
    val name: String,
    val code: String,

    @field:Field("desc")
    val description: String,
    val startDate: String,
    val endDate: String,
    @field:Field("cost")
    val estimatedCost: Long,
    val countryList: List<String>,

    @field:Version
    val version: Long?
)
