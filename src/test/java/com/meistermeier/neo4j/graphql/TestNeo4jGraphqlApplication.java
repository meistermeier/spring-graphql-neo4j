package com.meistermeier.neo4j.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.util.function.ThrowingConsumer;
import org.testcontainers.containers.Neo4jContainer;

/**
 * Starts the application with a fresh Neo4j testcontainer instance.
 * The start mechanism is created by using the recently (Spring Boot 3.1) added
 * {@link SpringApplication#from(ThrowingConsumer)} method.
 *
 * @author Gerrit Meier
 */
@TestConfiguration(proxyBeanMethods = false)
public class TestNeo4jGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.from(Neo4jGraphqlApplication::main)
				.with(TestNeo4jGraphqlApplication.class)
				.run(args);
	}

	@Bean
	@ServiceConnection
	public Neo4jContainer<?> neo4jContainer() {
		return new Neo4jContainer<>("neo4j:5").withRandomPassword();
	}

}
