package com.rbleuse.spring.reactive.couchbase.service

import com.rbleuse.spring.reactive.couchbase.model.Project
import com.rbleuse.spring.reactive.couchbase.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val repository: ProjectRepository
) {

    fun createProject(project: Project) = repository.save(project)

    fun findById(id: String) = repository.findById(id)

    fun findAll() = repository.findAll()

    fun deleteById(id: String) = repository.deleteById(id)

    fun findByName(name: String) = repository.findByName(name)

    fun findByNameLike(name: String) = repository.findByNameLike(name)

    fun findAllByIds(ids: List<String>) = repository.findAllByIdsNikl(ids)

    fun findByCountry(country: String) = repository.findAllByCountry(country)
}
