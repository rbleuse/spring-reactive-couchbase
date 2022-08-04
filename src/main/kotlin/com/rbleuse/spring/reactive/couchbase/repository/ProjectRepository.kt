package com.rbleuse.spring.reactive.couchbase.repository

import com.rbleuse.spring.reactive.couchbase.model.Project
import org.springframework.data.couchbase.repository.Query
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository
import org.springframework.data.repository.query.Param
import reactor.core.publisher.Flux

interface ProjectRepository : ReactiveCouchbaseRepository<Project, String> {
    fun findByName(name: String): Flux<Project>

    @Query("#{#n1ql.selectEntity} WHERE LOWER(name) LIKE '%#{[0]}%' AND #{#n1ql.filter}")
    fun findByNameLike(name: String): Flux<Project>

    @Query("#{#n1ql.selectEntity} WHERE meta().id IN \$ids AND #{#n1ql.filter}")
    fun findAllByIdsNikl(@Param("ids") ids: List<String>): Flux<Project>

    @Query("#{#n1ql.selectEntity} WHERE (SOME country IN countryList satisfies country = $1 END) AND #{#n1ql.filter}")
    fun findAllByCountry(country: String): Flux<Project>
}
