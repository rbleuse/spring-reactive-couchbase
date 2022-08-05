package com.rbleuse.spring.reactive.couchbase.config

import com.couchbase.client.core.msg.kv.DurabilityLevel
import com.couchbase.client.java.env.ClusterEnvironment
import com.couchbase.client.java.transactions.config.TransactionsConfig
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

    override fun configureEnvironment(builder: ClusterEnvironment.Builder) {
        builder.transactionsConfig(TransactionsConfig.builder().durabilityLevel(DurabilityLevel.NONE))
    }
}
