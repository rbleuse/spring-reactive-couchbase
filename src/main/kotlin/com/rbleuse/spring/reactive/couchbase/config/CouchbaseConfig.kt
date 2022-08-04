package com.rbleuse.spring.reactive.couchbase.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableReactiveCouchbaseRepositories
class CouchbaseConfig : AbstractCouchbaseConfiguration() {
    override fun getConnectionString(): String {
        return "couchbase://127.0.0.1"
    }

    override fun getUserName(): String {
        return "admin"
    }

    override fun getPassword(): String {
        return "password"
    }

    override fun getBucketName(): String {
        return "test"
    }
}
