package com.rbleuse.spring.reactive.couchbase.config

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.couchbase.CouchbaseClientFactory
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration
import org.springframework.data.couchbase.config.BeanNames
import org.springframework.data.couchbase.transaction.CouchbaseCallbackTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class CouchbaseConfig(
    private val properties: CouchbaseProperties
) : AbstractCouchbaseConfiguration() {
    override fun getConnectionString(): String {
        return properties.connectionString
    }

    override fun getUserName(): String {
        return properties.username
    }

    override fun getPassword(): String {
        return properties.password
    }

    override fun getBucketName(): String {
        return "test"
    }
}
