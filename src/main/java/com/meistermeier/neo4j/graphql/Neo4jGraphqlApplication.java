package com.meistermeier.neo4j.graphql;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.MetricsAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.neo4j.ConfigBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Neo4jGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jGraphqlApplication.class, args);
    }

    @Bean
    Configuration cypherDslConfiguration() {
        return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
    }

    /**
     * Makes the Neo4j driver use Micrometer for metrics reporting.
     */
    @Bean
    ConfigBuilderCustomizer configBuilderCustomizer() {
        return configBuilder -> configBuilder.withMetricsAdapter(MetricsAdapter.MICROMETER);
    }
}
