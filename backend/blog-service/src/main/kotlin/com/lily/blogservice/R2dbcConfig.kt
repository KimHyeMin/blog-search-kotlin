package com.lily.blogservice

import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.h2.H2ConnectionOption
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
@EnableR2dbcRepositories
class R2dbcConfig: AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory =
        H2ConnectionFactory(
            H2ConnectionConfiguration.builder()
                .inMemory("service")
                .username("sa")
                .build())

    @Bean
    fun h2DbInitializer(): ConnectionFactoryInitializer {
        val initializer:ConnectionFactoryInitializer = ConnectionFactoryInitializer()
        val resourceDatabasePopulator:ResourceDatabasePopulator = ResourceDatabasePopulator()
        resourceDatabasePopulator.addScript(ClassPathResource("schema.sql"))
        initializer.setConnectionFactory(connectionFactory())
        initializer.setDatabasePopulator(resourceDatabasePopulator)
        return initializer
    }
}